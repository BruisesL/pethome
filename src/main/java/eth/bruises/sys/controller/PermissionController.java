package eth.bruises.sys.controller;

import eth.bruises.sys.service.IPermissionService;
import eth.bruises.sys.domain.Permission;
import eth.bruises.sys.query.PermissionQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    public IPermissionService permissionService;

    /**
     * 接口：添加或修改
     * @param permission  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Permission permission){ 
        if( permission.getId()!=null){
		permissionService.update(permission);
        }else{
                permissionService.add(permission);
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
        permissionService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        permissionService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public Permission selectOne(@PathVariable("id")Long id) {
        return permissionService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<Permission> selectAll(){
        return permissionService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<Permission> page(@RequestBody PermissionQuery query) {
        return permissionService.page(query);
    }
}
