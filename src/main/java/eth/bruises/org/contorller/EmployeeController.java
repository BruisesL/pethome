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
/**
 * 接口文档的注解
 */
@Api(value = "员工的API",description="员工相关的CRUD功能")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "查询所有" )
    @GetMapping
    public List<Employee> selectAll() {
        return employeeService.selectAll();
    }

    @ApiOperation(value = "根据id查询" )
    @GetMapping("{id}")
    public Employee selectOne(@ApiParam(value = "ID属性", required = true) @PathVariable("id") Long id) {
        return employeeService.selectOne(id);
    }

    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Employee employee) {
        try {
            if (null == employee.getId()) {
                employeeService.add(employee);
            } else {
                employeeService.update(employee);
            }
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("系统繁忙！");
        }
    }

    @DeleteMapping("{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try{
            employeeService.delete(id);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("系统繁忙！");
        }
    }

    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long>ids){
        try {
            employeeService.batchDel(ids);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("系统繁忙！");
        }
    }

    @PostMapping
    public PageInfo<Employee> page(@RequestBody EmployeeQuery query){
        return employeeService.page(query);
    }
}
