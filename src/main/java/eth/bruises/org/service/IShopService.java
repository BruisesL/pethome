package eth.bruises.org.service;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.org.domain.Shop;
import eth.bruises.basic.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bruises
 * @since 2023-05-27
 */
public interface IShopService extends BaseService<Shop> {
    /**
     * 店铺入驻的方法
     * @param shop
     * @return
     */
    AjaxResult settlement(Shop shop);
}
