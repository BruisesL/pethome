package eth.bruises.org.service;

import eth.bruises.basic.service.BaseService;
import eth.bruises.org.domain.Department;

import java.util.List;

/**
 * 部门的service层
 * @author bruises
 */
public interface IDepartmentService extends BaseService<Department> {
    /**
     * 获取部门树
     * @return
     */
    List<Department> tree();
}
