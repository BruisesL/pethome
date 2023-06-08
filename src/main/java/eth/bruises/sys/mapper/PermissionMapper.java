package eth.bruises.sys.mapper;

import eth.bruises.sys.domain.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    Permission loadBySn(String permissionSn);

    List<String> loadByLogininfoId(Long logininfoId);
}
