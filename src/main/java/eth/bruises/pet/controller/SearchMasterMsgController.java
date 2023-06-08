package eth.bruises.pet.controller;

import eth.bruises.pet.service.ISearchMasterMsgService;
import eth.bruises.pet.domain.SearchMasterMsg;
import eth.bruises.pet.query.SearchMasterMsgQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@Validated
@RestController
@RequestMapping("/searchMasterMsg")
public class SearchMasterMsgController {

    @Autowired
    public ISearchMasterMsgService searchMasterMsgService;

    /**
     * 接口：添加或修改
     * @param searchMasterMsg  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody SearchMasterMsg searchMasterMsg){ 
        if( searchMasterMsg.getId()!=null){
		searchMasterMsgService.update(searchMasterMsg);
        }else{
                searchMasterMsgService.add(searchMasterMsg);
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
        searchMasterMsgService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        searchMasterMsgService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public SearchMasterMsg selectOne(@PathVariable("id")Long id) {
        return searchMasterMsgService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<SearchMasterMsg> selectAll(){
        return searchMasterMsgService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<SearchMasterMsg> page(@RequestBody SearchMasterMsgQuery query) {
        return searchMasterMsgService.page(query);
    }

    @PostMapping("/publish")
    public AjaxResult publish(@RequestBody SearchMasterMsg msg) {
        searchMasterMsgService.publish(msg);
        return AjaxResult.success();
    }
}
