package eth.bruises.pet.controller;

import eth.bruises.pet.service.IPetDetailService;
import eth.bruises.pet.domain.PetDetail;
import eth.bruises.pet.query.PetDetailQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/petDetail")
public class PetDetailController {

    @Autowired
    public IPetDetailService petDetailService;

    /**
     * 接口：添加或修改
     * @param petDetail  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody PetDetail petDetail){ 
        if( petDetail.getId()!=null){
		petDetailService.update(petDetail);
        }else{
                petDetailService.add(petDetail);
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
        petDetailService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        petDetailService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public PetDetail selectOne(@PathVariable("id")Long id) {
        return petDetailService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<PetDetail> selectAll(){
        return petDetailService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<PetDetail> page(@RequestBody PetDetailQuery query) {
        return petDetailService.page(query);
    }
}
