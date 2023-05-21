package eth.bruises.org.service.impl;

import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.org.domain.Department;
import eth.bruises.org.service.IDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bruises
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
    @Override
    public List<Department> tree() {
        // 获取全量部门数据
        List<Department> departments = selectAll();
        // 放入map key:id value:Department
        Map<Long, Department> map = departments.stream().collect(Collectors.toMap(Department::getId, d -> d));
        List<Department> result = new ArrayList<>();
        // 遍历判断是否有上级部门，若没有则为顶级部门，放入List，否则寻找父级放入
        for (Department department:departments) {
            if (null == department.getParentId()) {
                result.add(department);
            } else {
                Department parent = map.get(department.getParentId());
                if (null != parent) {
                    // 判断父部门中的Children是否为空
                    if (CollectionUtils.isEmpty(parent.getChildren())) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(department);
                }
            }
        }
        return result;
    }
}
