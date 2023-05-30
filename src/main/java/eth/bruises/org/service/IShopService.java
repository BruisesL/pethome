package eth.bruises.org.service;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.org.domain.Shop;
import eth.bruises.basic.service.BaseService;
import eth.bruises.org.dto.ShopAuditLogDto;
import eth.bruises.org.vo.ShopStatisticsVo;

import java.util.List;

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

    /**
     * 店铺审批拒绝
     * @param dto
     */
    void reject(ShopAuditLogDto dto);
    /**
     * 店铺审批通过
     * @param dto
     */
    void pass(ShopAuditLogDto dto);

    /**
     * 店铺激活
     * @param shopId
     */
    void active(Long shopId);

    /**
     * 批量添加
     * @param shops
     */
    void batchAdd(List<Shop> shops);

    /**
     * 统计店铺状态数据
     * @return
     */
    ShopStatisticsVo getStateStatistics();
}
