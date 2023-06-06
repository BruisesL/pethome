package eth.bruises.sys.controller;

import eth.bruises.sys.service.IRoleService;
import eth.bruises.sys.domain.Role;
import eth.bruises.sys.query.RoleQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public IRoleService roleService;

    /**
     * 接口：添加或修改
     * @param role  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Role role){ 
        if( role.getId()!=null){
		roleService.update(role);
        }else{
                roleService.add(role);
	}
        return AjaxResult.success();
       
    }

    /**
    * 接口：删除
    * @param id
    * @return AjaxResult 响应给前端
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        roleService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        roleService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public Role selectOne(@PathVariable("id")Long id) {
        return roleService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<Role> selectAll(){
        return roleService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<Role> page(@RequestBody RoleQuery query) {
        return roleService.page(query);
    }
}
