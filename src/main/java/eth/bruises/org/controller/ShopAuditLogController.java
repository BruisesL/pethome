package eth.bruises.org.controller;

import eth.bruises.org.service.IShopAuditLogService;
import eth.bruises.org.domain.ShopAuditLog;
import eth.bruises.org.query.ShopAuditLogQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/shopAuditLog")
public class ShopAuditLogController {

    @Autowired
    public IShopAuditLogService shopAuditLogService;

    /**
     * 接口：添加或修改
     * @param shopAuditLog  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody ShopAuditLog shopAuditLog){ 
        if( shopAuditLog.getId()!=null){
		shopAuditLogService.update(shopAuditLog);
        }else{
                shopAuditLogService.add(shopAuditLog);
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
        shopAuditLogService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        shopAuditLogService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public ShopAuditLog selectOne(@PathVariable("id")Long id) {
        return shopAuditLogService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<ShopAuditLog> selectAll(){
        return shopAuditLogService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<ShopAuditLog> page(@RequestBody ShopAuditLogQuery query) {
        return shopAuditLogService.page(query);
    }
}
