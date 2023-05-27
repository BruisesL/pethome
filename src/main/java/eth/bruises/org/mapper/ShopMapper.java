package eth.bruises.org.mapper;

import eth.bruises.org.domain.Shop;
import eth.bruises.basic.mapper.BaseMapper;

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
}
