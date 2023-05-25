package eth.bruises.org.service.impl;

import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.service.IEmployeeService;
import eth.bruises.org.service.IShopService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bruises
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {
    @Override
    public void add(Shop shop) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String registerTime = sdf.format(new Date());
        shop.setRegisterTime(registerTime);
        super.add(shop);
    }
}
