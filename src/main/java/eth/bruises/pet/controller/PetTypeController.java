package eth.bruises.pet.controller;

import eth.bruises.pet.service.IPetTypeService;
import eth.bruises.pet.domain.PetType;
import eth.bruises.pet.query.PetTypeQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/petType")
public class PetTypeController {

    @Autowired
    public IPetTypeService petTypeService;

    /**
     * 接口：添加或修改
     * @param petType  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody PetType petType){ 
        if( petType.getId()!=null){
		petTypeService.update(petType);
        }else{
                petTypeService.add(petType);
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
        petTypeService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        petTypeService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public PetType selectOne(@PathVariable("id")Long id) {
        return petTypeService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<PetType> selectAll(){
        return petTypeService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<PetType> page(@RequestBody PetTypeQuery query) {
        return petTypeService.page(query);
    }
}
