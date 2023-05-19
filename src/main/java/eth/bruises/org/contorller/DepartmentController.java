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
//接口文档的注解
@Api(value = "部门的API",description="部门相关的CRUD功能")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "查询所有" )
    @GetMapping
    public List<Department> selectAll() {
        return departmentService.selectAll();
    }

    @ApiOperation(value = "根据id查询" )
    @GetMapping("{id}")
    public Department selectOne(@ApiParam(value = "ID属性", required = true) @PathVariable("id") Long id) {
        return departmentService.selectOne(id);
    }

    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Department department) {
        try {
            if (null == department.getId()) {
                departmentService.add(department);
            } else {
                departmentService.update(department);
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
            departmentService.delete(id);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("系统繁忙！");
        }
    }

    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long>ids){
        try {
            departmentService.batchDel(ids);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("系统繁忙！");
        }
    }

    @PostMapping
    public PageInfo<Department> page(DepartmentQuery query){
        return departmentService.page(query);
    }
}
