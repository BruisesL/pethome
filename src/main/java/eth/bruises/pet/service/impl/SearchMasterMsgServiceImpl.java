package eth.bruises.pet.service.impl;

import eth.bruises.basic.utils.*;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.mapper.EmployeeMapper;
import eth.bruises.org.mapper.ShopMapper;
import eth.bruises.pet.domain.SearchMasterMsg;
import eth.bruises.pet.domain.SearchMasterMsgAuditLog;
import eth.bruises.pet.mapper.SearchMasterMsgAuditLogMapper;
import eth.bruises.pet.mapper.SearchMasterMsgMapper;
import eth.bruises.pet.service.ISearchMasterMsgService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.user.domain.Logininfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务实现类：
 * @author bruises
 */
@Service
public class SearchMasterMsgServiceImpl extends BaseServiceImpl<SearchMasterMsg> implements ISearchMasterMsgService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SearchMasterMsgMapper searchMasterMsgMapper;

    @Autowired
    private SearchMasterMsgAuditLogMapper searchMasterMsgAuditLogMapper;

    @Override
    @Transactional
    public void publish(SearchMasterMsg msg) {
        // 定义flag判断是否违规
        Boolean isSearchMasterMsgAuditLogTag = false;
        // 百度AI合规性校验
        String resources = msg.getResources();
        Boolean imgCensorTag = true;
        if (resources != null) {
            imgCensorTag = BaiduAiUtil.imageCensor(resources);
        }
        String text = msg.getName() + ";" + msg.getTitle() + ";" + msg.getAddress() + ";" + msg.getNote();
        Boolean textCensorTag = BaiduAiUtil.textCensor(text);

        Logininfo logininfo = LoginContext.getLogininfo();
        if (textCensorTag && imgCensorTag) {
            List<Shop> shops =  shopMapper.findByState(3);
            // 不违规
            // 校验附近50km的店铺
            Shop nearestShop = DistanceUtil.getNearestShop(msg.getAddress(), shops);
            // 找得到则分配给该店铺，否则不处理，直接进入待寻主池
            if (nearestShop != null) {
                msg.setShopId(nearestShop.getId());
                // 发送短信通知平台管理员有新订单
                Employee employee = employeeMapper.selectOne(nearestShop.getAdminId());
//                SendSmsUtil.sendSms(employee.getPhone(), "您好，平台收到新的寻主订单，请及时查收。");
                System.out.println("您好，平台收到新的寻主订单，请及时查收。");
            }
            msg.setState(1);
        } else {
            // 违规
            // 修改状态 并且记录日志 存表
            isSearchMasterMsgAuditLogTag = true;
            msg.setState(-1);
        }

        msg.setUserId(logininfo.getId());
        searchMasterMsgMapper.add(msg);

        if (isSearchMasterMsgAuditLogTag) {
            SearchMasterMsgAuditLog log = new SearchMasterMsgAuditLog();
            log.setState(0);
            log.setMsgId(msg.getId());
            log.setNote("百度AI审核失败");
            searchMasterMsgAuditLogMapper.add(log);
        }

    }
}
