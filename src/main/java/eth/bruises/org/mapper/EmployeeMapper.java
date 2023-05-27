package eth.bruises.org.mapper;

import eth.bruises.basic.mapper.BaseMapper;
import eth.bruises.org.domain.Department;
import eth.bruises.org.domain.Employee;

/**
 * 员工mapper
 * @author bruises
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    Employee findByPhone(String phone);
}
