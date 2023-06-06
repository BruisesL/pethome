package eth.bruises.sys.service;

import eth.bruises.sys.domain.Permission;
import eth.bruises.basic.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bruises
 * @since 2023-06-06
 */
public interface IPermissionService extends BaseService<Permission> {
    void scanPermission();

}
