package eth.bruises.user.controller;

import eth.bruises.user.service.ILogininfoService;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.query.LogininfoQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/logininfo")
public class LogininfoController {

    @Autowired
    public ILogininfoService logininfoService;

    /**
     * 接口：添加或修改
     * @param logininfo  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Logininfo logininfo){ 
        if( logininfo.getId()!=null){
		logininfoService.update(logininfo);
        }else{
                logininfoService.add(logininfo);
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
        logininfoService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        logininfoService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public Logininfo selectOne(@PathVariable("id")Long id) {
        return logininfoService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<Logininfo> selectAll(){
        return logininfoService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<Logininfo> page(@RequestBody LogininfoQuery query) {
        return logininfoService.page(query);
    }
}
