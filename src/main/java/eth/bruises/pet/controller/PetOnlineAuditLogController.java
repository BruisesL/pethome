package eth.bruises.pet.controller;

import eth.bruises.pet.service.IPetOnlineAuditLogService;
import eth.bruises.pet.domain.PetOnlineAuditLog;
import eth.bruises.pet.query.PetOnlineAuditLogQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/petOnlineAuditLog")
public class PetOnlineAuditLogController {

    @Autowired
    public IPetOnlineAuditLogService petOnlineAuditLogService;

    /**
     * 接口：添加或修改
     * @param petOnlineAuditLog  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody PetOnlineAuditLog petOnlineAuditLog){ 
        if( petOnlineAuditLog.getId()!=null){
		petOnlineAuditLogService.update(petOnlineAuditLog);
        }else{
                petOnlineAuditLogService.add(petOnlineAuditLog);
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
        petOnlineAuditLogService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        petOnlineAuditLogService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public PetOnlineAuditLog selectOne(@PathVariable("id")Long id) {
        return petOnlineAuditLogService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<PetOnlineAuditLog> selectAll(){
        return petOnlineAuditLogService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<PetOnlineAuditLog> page(@RequestBody PetOnlineAuditLogQuery query) {
        return petOnlineAuditLogService.page(query);
    }
}
