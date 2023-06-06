package eth.bruises.sys.service;

import eth.bruises.sys.domain.Menu;
import eth.bruises.basic.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bruises
 * @since 2023-06-06
 */
public interface IMenuService extends BaseService<Menu> {

    List<Menu> menuTree();
}
