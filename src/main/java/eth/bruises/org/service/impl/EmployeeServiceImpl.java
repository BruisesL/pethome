package eth.bruises.org.service.impl;

import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.org.domain.Department;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.mapper.EmployeeMapper;
import eth.bruises.org.service.IDepartmentService;
import eth.bruises.org.service.IEmployeeService;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.domain.User;
import eth.bruises.user.mapper.LogininfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bruises
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private LogininfoMapper logininfoMapper;

    @Transactional
    @Override
    public void add(Employee employee) {
        Logininfo logininfo = copyEmployee(employee);
        logininfoMapper.add(logininfo);
        employee.setLogininfoId(logininfo.getId());
        super.add(employee);
    }

    @Transactional
    @Override
    public void update(Employee employee) {
        // 通过employee.logininfoId查询logininfo对象
        Logininfo logininfo = logininfoMapper.selectOne(employee.getLogininfoId());
        if (logininfo != null) {
            // copy参数到新的logininfo对象中
            Logininfo newLogininfo = copyEmployee(employee);
            newLogininfo.setId(logininfo.getId());
            // 调用logininfo的更新方法
            logininfoMapper.update(newLogininfo);
        }
        super.update(employee);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Employee employee = employeeMapper.selectOne(id);
        if (employee != null) {
            Long logininfoId = employee.getLogininfoId();
            if (logininfoId != null) {
                logininfoMapper.delete(logininfoId);
            }
        }
        super.delete(id);
    }

    @Transactional
    @Override
    public void batchDel(List<Long> ids) {
        List<Long> logininfoIds = ids.stream()
                .map(id -> employeeMapper.selectOne(id))
                .map(Employee::getLogininfoId)
                .collect(Collectors.toList());
        logininfoMapper.batchDel(logininfoIds);
        super.batchDel(ids);
    }

    private Logininfo copyEmployee(Employee employee) {
        Logininfo logininfo = new Logininfo();
        BeanUtils.copyProperties(employee, logininfo);
        logininfo.setType(0);
        logininfo.setDisable(employee.getState() == 1);
        return logininfo;
    }
}
