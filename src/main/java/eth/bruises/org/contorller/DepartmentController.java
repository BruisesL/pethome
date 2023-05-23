package eth.bruises.org.contorller;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import eth.bruises.org.domain.Department;
import eth.bruises.org.query.DepartmentQuery;
import eth.bruises.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门controller层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/department")
@Api(value = "部门的API", description = "部门相关的CRUD功能")
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

    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Department department) {

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

}
