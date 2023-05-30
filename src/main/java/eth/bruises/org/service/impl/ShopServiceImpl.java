package eth.bruises.org.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.BaiduAiUtil;
import eth.bruises.basic.utils.SendEmailUtil;
import eth.bruises.org.bo.ShopStatisticsBo;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.domain.ShopAuditLog;
import eth.bruises.org.dto.ShopAuditLogDto;
import eth.bruises.org.mapper.EmployeeMapper;
import eth.bruises.org.mapper.ShopAuditLogMapper;
import eth.bruises.org.mapper.ShopMapper;
import eth.bruises.org.service.IShopService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.org.vo.ShopStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务实现类：
 *
 * @author bruises
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ShopAuditLogMapper shopAuditLogMapper;

    /**
     * 批量添加
     *
     * @param shops
     */
    @Override
    public void batchAdd(List<Shop> shops) {
        shopMapper.batchAdd(shops);
    }

    /**
     * 店铺状态统计
     *
     * @return
     */
    @Override
    public ShopStatisticsVo getStateStatistics() {
        List<ShopStatisticsBo> boList = shopMapper.getStateStatistics();
        List<Integer> states = boList.stream().map(ShopStatisticsBo::getState).collect(Collectors.toList());
        List<Integer> counts = boList.stream().map(ShopStatisticsBo::getCount).collect(Collectors.toList());
        return new ShopStatisticsVo(states, counts);
    }

    /**
     * 店铺入驻实现
     *
     * @param shop
     * @return
     */
    @Transactional
    @Override
    public AjaxResult settlement(Shop shop) {
        // 1.参数校验 JSR303校验
        // 2.业务校验
        // 2.0 百度AI审核
        StringBuffer text = new StringBuffer();
        text.append(shop.getName()).append(shop.getAddress()).append(shop.getAdmin().getUsername());
        if (!BaiduAiUtil.textCensor(text.toString())) {
            throw new GlobalException(GlobalExceptionEnum.TEXT_CENSOR_ERROR);
        }
        if (!BaiduAiUtil.imageCensor(shop.getLogo())) {
            throw new GlobalException(GlobalExceptionEnum.IMG_CENSOR_ERROR);
        }
        // 2.1.店铺是否存在
        String shopName = shop.getName();
        Shop existShop = shopMapper.findByName(shopName);
        Employee admin = shop.getAdmin();
        String adminPhone = admin.getPhone();
        Employee existAdmin = employeeMapper.findByPhone(adminPhone);
        // 店铺不为空 是驳回注册
        if (existShop != null) {
            // 判断状态是否是驳回（4），则说明是驳回后的再注册
            if (!existShop.getState().equals(4)) {
                throw new GlobalException(GlobalExceptionEnum.SHOP_EXISTS);
            }
            // 3.1.存入shop数据
            shop.setState(1);
            shopMapper.update(shop);
            // 删除原店铺管理员
            employeeMapper.delete(existShop.getAdminId());
        }
        // 店铺为空 则第一次注册
        else {
            // 2.2.用户手机号是否已存在
            if (existAdmin != null) {
                throw new GlobalException(GlobalExceptionEnum.SHOP_ADMIN_EXISTS);
            }

            // 通过校验 第一次注册
            // 3.1.存入shop数据
            shop.setState(1);
            shopMapper.add(shop);
        }

        // 3.2.存入员工数据
        admin.setState(0);
        admin.setShopId(shop.getId());
        // 生成盐值
        String salt = RandomUtil.randomString(32);
        admin.setSalt(salt);
        // 密码加盐加密
        String password = SecureUtil.md5(salt + admin.getPassword());
        admin.setPassword(password);
        employeeMapper.add(admin);

        // 回写adminId
        shop.setAdminId(admin.getId());
        shopMapper.update(shop);
        return AjaxResult.success();
    }

    /**
     * 店铺审批拒绝方法实现
     *
     * @param dto
     */
    @Transactional
    @Override
    public void reject(ShopAuditLogDto dto) {
        // 参数校验 JSR303
        // 业务校验
        Shop shop = shopCheck(dto);
        // 业务逻辑
        // 改状态
        shop.setState(4);
        shopMapper.update(shop);
        // 发邮件
        Employee employee = employeeMapper.selectOne(shop.getAdminId());
        String emailMessage = "<h3>你的店铺审核未通过，请<a href='http://127.0.0.1:8081/#/shopRegister?shopId=" + dto.getShopId() + "'>点击这里</a>修改入驻信息重新提交</h3>";
        SendEmailUtil.sendMail(employee.getEmail(), "店铺入驻审批失败通知", emailMessage);
        // 存日志
        saveLog(dto, 4);
    }

    /**
     * 店铺审批通过方法实现
     *
     * @param dto
     */
    @Transactional
    @Override
    public void pass(ShopAuditLogDto dto) {
        // 参数校验 JSR303
        // 业务校验
        Shop shop = shopCheck(dto);
        // 业务逻辑
        // 改状态
        shop.setState(2);
        shopMapper.update(shop);
        // 发邮件
        Employee employee = employeeMapper.selectOne(shop.getAdminId());
        String emailMessage = "<h3>你的店铺已经审核通过，请<a href='http://127.0.0.1:8081/#/shopActive?shopId=" + dto.getShopId() + "'>点击这里</a>激活邮件</h3>";
        SendEmailUtil.sendMail(employee.getEmail(), "店铺入驻激活通知", emailMessage);
        // 存日志
        saveLog(dto, 2);
    }

    /**
     * 店铺激活方法实现
     *
     * @param shopId
     */
    @Transactional
    @Override
    public void active(Long shopId) {
        Shop shop = shopMapper.selectOne(shopId);
        if (null == shop) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_NOT_EXIST);
        }
        if (!shop.getState().equals(2)) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_STATE_ERROR);
        }
        shop.setState(3);
        shopMapper.update(shop);

        Employee employee = employeeMapper.selectOne(shop.getAdminId());
        if (null == employee) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_ADMIN_NOT_EXIST);
        }
        employee.setState(1);
        employeeMapper.update(employee);
    }

    /**
     * 保存日志方法抽取
     *
     * @param dto
     * @param state
     */
    private void saveLog(ShopAuditLogDto dto, Integer state) {
        ShopAuditLog log = new ShopAuditLog();
        log.setShopId(dto.getShopId());
        log.setAuditId(1L);
        log.setState(state);
        log.setNote(dto.getNote());
        shopAuditLogMapper.add(log);
    }

    /**
     * 店铺检查方法抽取
     *
     * @param dto
     * @return
     */
    private Shop shopCheck(ShopAuditLogDto dto) {
        // 业务校验
        // 是否存在店铺
        Shop shop = shopMapper.selectOne(dto.getShopId());
        if (shop == null) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_NOT_EXIST);
        }
        // 是否店铺已审核
        if (!shop.getState().equals(1)) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_STATE_ERROR);

        }
        return shop;
    }

}
