package eth.bruises.sys.mapper;

import eth.bruises.sys.domain.Menu;
import eth.bruises.basic.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bruises
 * @since 2023-06-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> loadByLogininfoId(Long logininfoId);
}
