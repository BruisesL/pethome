package eth.bruises.org.mapper;

import eth.bruises.org.bo.ShopStatisticsBo;
import eth.bruises.org.domain.Shop;
import eth.bruises.basic.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bruises
 * @since 2023-05-27
 */
public interface ShopMapper extends BaseMapper<Shop> {
    /**
     * 通过店铺名查找店铺
     * @param name
     * @return
     */
    Shop findByName(String name);

    /**
     * 批量添加
     * @param shops
     */
    void batchAdd(List<Shop> shops);

    /**
     * 统计状态个数
     * @return
     */
    List<ShopStatisticsBo> getStateStatistics();
}
