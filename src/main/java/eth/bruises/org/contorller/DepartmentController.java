package eth.bruises.org.contorller;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import eth.bruises.basic.validation.validateGroups.Add;
import eth.bruises.basic.validation.validateGroups.Update;
import eth.bruises.org.domain.Department;
import eth.bruises.org.domain.Employee;
import eth.bruises.org.query.DepartmentQuery;
import eth.bruises.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 部门controller层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/department")
@Api(value = "部门的API", description = "部门相关的CRUD功能")
@Validated
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "查询所有")
    @GetMapping
    public List<Department> selectAll() {
        return departmentService.selectAll();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("{id}")
    public Department selectOne(@ApiParam(value = "ID属性", required = true) @PathVariable("id") Long id) {
        return departmentService.selectOne(id);
    }

    @PutMapping("/add")
    public AjaxResult add(@Validated({Add.class}) @RequestBody Department department) {
        departmentService.add(department);
        return AjaxResult.success();
    }
    @PutMapping("/update")
    public AjaxResult update(@Validated({Update.class}) @RequestBody Department department) {
        departmentService.update(department);
        return AjaxResult.success();
    }

    @PutMapping
    public AjaxResult addOrUpdate(@Validated @RequestBody Department department) {

        if (null == department.getId()) {
            departmentService.add(department);
        } else {
            departmentService.update(department);
        }
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
        return AjaxResult.success();
    }

    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids) {
        departmentService.batchDel(ids);
        return AjaxResult.success();
    }

    @PostMapping
    public PageInfo<Department> page(@RequestBody DepartmentQuery query) {
        return departmentService.page(query);
    }

    @GetMapping("/tree")
    public List<Department> tree() {
        return departmentService.tree();
    }

    /**
     * ================参数校验测试===================
     */
//    @PostMapping("/test")
//    public AjaxResult test(@Valid @RequestBody Employee employee) {
//        return AjaxResult.success();
//    }
//
//    @GetMapping("/test/{age}")
//    public AjaxResult test2(@Min(value = 18, message = "年龄不能小于18岁！") @PathVariable("age")Integer age) {
//        return AjaxResult.success();
//    }
//
//    // localhost:8080/department/test2?age = xxxx， 下面这个方法不是restful风格，是普通地址传参
//    @GetMapping("/test2")
//    public AjaxResult test3(@Min(value = 18, message = "年龄不能小于18岁！") Integer age) {
//        return AjaxResult.success();
//    }

}
