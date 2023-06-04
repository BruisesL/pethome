package eth.bruises.user.controller;

import eth.bruises.user.dto.WxBindingDto;
import eth.bruises.user.service.IWxuserService;
import eth.bruises.user.domain.Wxuser;
import eth.bruises.user.query.WxuserQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/wxuser")
public class WxuserController {

    @Autowired
    public IWxuserService wxuserService;

    /**
     * 接口：添加或修改
     * @param wxuser  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Wxuser wxuser){ 
        if( wxuser.getId()!=null){
		wxuserService.update(wxuser);
        }else{
                wxuserService.add(wxuser);
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
        wxuserService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        wxuserService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public Wxuser selectOne(@PathVariable("id")Long id) {
        return wxuserService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<Wxuser> selectAll(){
        return wxuserService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<Wxuser> page(@RequestBody WxuserQuery query) {
        return wxuserService.page(query);
    }

    /**
     * 微信用户绑定帐号
     * @param wxBindingDto
     * @return
     */
    @PostMapping("/wxBinding")
    public AjaxResult wxBinding(@Valid @RequestBody WxBindingDto wxBindingDto){
        return AjaxResult.success(wxuserService.wxBinding(wxBindingDto));
    }

}
