package eth.bruises.sys.mapper;

import eth.bruises.sys.domain.Role;
import eth.bruises.basic.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bruises
 * @since 2023-06-06
 */
public interface RoleMapper extends BaseMapper<Role> {

    void batchRoleMenu(@Param("id") Long id, @Param("menuIds") List<Long> menuIds);

    void batchRolePermission(@Param("id") Long id, @Param("permissionIds") List<Long> permissionIds);

    void deleteRoleMenu(Long id);

    void deleteRolePermission(Long id);

    void batchDelRoleMenu(List<Long> ids);

    void batchDelRolePermission(List<Long> ids);
}
