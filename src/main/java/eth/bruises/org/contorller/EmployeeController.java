package eth.bruises.org.contorller;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.query.EmployeeQuery;
import eth.bruises.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工controller层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/employee")
@Api(value = "员工的API", description = "员工相关的CRUD功能")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "查询所有")
    @GetMapping
    public List<Employee> selectAll() {
        return employeeService.selectAll();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("{id}")
    public Employee selectOne(@ApiParam(value = "ID属性", required = true) @PathVariable("id") Long id) {
        return employeeService.selectOne(id);
    }

    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Employee employee) {
        if (null == employee.getId()) {
            employeeService.add(employee);
        } else {
            employeeService.update(employee);
        }
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return AjaxResult.success();
    }

    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids) {
        employeeService.batchDel(ids);
        return AjaxResult.success();
    }

    @PostMapping
    public PageInfo<Employee> page(@RequestBody EmployeeQuery query) {
        return employeeService.page(query);
    }
}
