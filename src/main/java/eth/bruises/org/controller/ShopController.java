package eth.bruises.org.controller;

import eth.bruises.org.service.IShopService;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.query.ShopQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后端接口类；
 *
 * @author bruises
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    public IShopService shopService;

    /**
     * 接口：添加或修改
     *
     * @param shop 传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Shop shop) {
        if (shop.getId() != null) {
            shopService.update(shop);
        } else {
            shopService.add(shop);
        }
        return AjaxResult.success();

    }

    /**
     * 接口：删除
     *
     * @param id
     * @return AjaxResult 响应给前端
     */
    @DeleteMapping(value = "/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        shopService.delete(id);
        return AjaxResult.success();
    }

    /**
     * 接口：批量删除
     *
     * @param ids
     * @return AjaxResult 响应给前端
     */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids) {
        shopService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
     * 接口：查询单个对象
     *
     * @param id
     */
    @GetMapping("/{id}")
    public Shop selectOne(@PathVariable("id") Long id) {
        return shopService.selectOne(id);
    }


    /**
     * 接口：查询所有
     *
     * @return
     */
    @GetMapping
    public List<Shop> selectAll() {
        return shopService.selectAll();
    }


    /**
     * 接口：分页查询或高级查询
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @PostMapping
    public PageInfo<Shop> page(@RequestBody ShopQuery query) {
        return shopService.page(query);
    }

    @ApiOperation(value = "店铺入驻",notes = "")
    @PostMapping("/settlement")
    public AjaxResult settlement(@RequestBody Shop shop) {
        return shopService.settlement(shop);
    }


}
