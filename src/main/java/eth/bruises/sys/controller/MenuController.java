package eth.bruises.sys.controller;

import eth.bruises.sys.service.IMenuService;
import eth.bruises.sys.domain.Menu;
import eth.bruises.sys.query.MenuQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    public IMenuService menuService;

    /**
     * 接口：添加或修改
     * @param menu  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Menu menu){ 
        if( menu.getId()!=null){
		menuService.update(menu);
        }else{
                menuService.add(menu);
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
        menuService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        menuService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public Menu selectOne(@PathVariable("id")Long id) {
        return menuService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<Menu> selectAll(){
        return menuService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<Menu> page(@RequestBody MenuQuery query) {
        return menuService.page(query);
    }

    @GetMapping("/menuTree")
    public List<Menu> menuTree() {
        return menuService.menuTree();
    }
}
