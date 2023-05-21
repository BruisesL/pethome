package eth.bruises.org.service.impl;

import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.org.domain.Department;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.service.IDepartmentService;
import eth.bruises.org.service.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author bruises
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
}
