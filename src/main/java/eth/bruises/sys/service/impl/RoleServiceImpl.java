package eth.bruises.sys.service.impl;

import eth.bruises.sys.domain.Role;
import eth.bruises.sys.mapper.RoleMapper;
import eth.bruises.sys.service.IRoleService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务实现类：
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void add(Role role) {
        super.add(role);

        roleMapper.batchRoleMenu(role.getId(), role.getMenus());
        roleMapper.batchRolePermission(role.getId(), role.getPermissions());

    }


    @Override
    public void update(Role role) {
        super.update(role);
        roleMapper.deleteRoleMenu(role.getId());
        roleMapper.deleteRolePermission(role.getId());
        roleMapper.batchRoleMenu(role.getId(), role.getMenus());
        roleMapper.batchRolePermission(role.getId(), role.getPermissions());
    }


    @Override
    public void delete(Long id) {
        roleMapper.deleteRoleMenu(id);
        roleMapper.deleteRolePermission(id);
        super.delete(id);
    }


    @Override
    public void batchDel(List<Long> ids) {
        super.batchDel(ids);
        roleMapper.batchDelRoleMenu(ids);
        roleMapper.batchDelRolePermission(ids);
    }
}
