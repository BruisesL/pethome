package eth.bruises.org.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.mapper.EmployeeMapper;
import eth.bruises.org.mapper.ShopMapper;
import eth.bruises.org.service.IShopService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public AjaxResult settlement(Shop shop) {
        // 1.参数校验 JSR303校验
        // 2.业务校验
            // 2.1.店铺是否存在
        String shopName = shop.getName();
        Shop existShop = shopMapper.findByName(shopName);
        if (existShop != null) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_EXISTS);
        }
            // 2.2.用户手机号是否已存在
        Employee admin = shop.getAdmin();
        String adminPhone = admin.getPhone();
        Employee existAdmin = employeeMapper.findByPhone(adminPhone);
        if (existAdmin != null) {
            throw new GlobalException(GlobalExceptionEnum.SHOP_ADMIN_EXISTS);
        }
        // 3.业务实现
            // 3.1.存入shop数据
        shop.setState(1);
        shopMapper.add(shop);
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
}
