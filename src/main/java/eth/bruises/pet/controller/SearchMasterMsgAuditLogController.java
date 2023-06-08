package eth.bruises.pet.controller;

import eth.bruises.pet.service.ISearchMasterMsgAuditLogService;
import eth.bruises.pet.domain.SearchMasterMsgAuditLog;
import eth.bruises.pet.query.SearchMasterMsgAuditLogQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/searchMasterMsgAuditLog")
public class SearchMasterMsgAuditLogController {

    @Autowired
    public ISearchMasterMsgAuditLogService searchMasterMsgAuditLogService;

    /**
     * 接口：添加或修改
     * @param searchMasterMsgAuditLog  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody SearchMasterMsgAuditLog searchMasterMsgAuditLog){ 
        if( searchMasterMsgAuditLog.getId()!=null){
		searchMasterMsgAuditLogService.update(searchMasterMsgAuditLog);
        }else{
                searchMasterMsgAuditLogService.add(searchMasterMsgAuditLog);
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
        searchMasterMsgAuditLogService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        searchMasterMsgAuditLogService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public SearchMasterMsgAuditLog selectOne(@PathVariable("id")Long id) {
        return searchMasterMsgAuditLogService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<SearchMasterMsgAuditLog> selectAll(){
        return searchMasterMsgAuditLogService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<SearchMasterMsgAuditLog> page(@RequestBody SearchMasterMsgAuditLogQuery query) {
        return searchMasterMsgAuditLogService.page(query);
    }
}
