package eth.bruises.org.contorller;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.query.ShopQuery;
import eth.bruises.org.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺controller层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/shop")
@Api(value = "店铺的API", description = "店铺相关的CRUD功能")
public class ShopController {
    @Autowired
    private IShopService shopService;

    @ApiOperation(value = "查询所有")
    @GetMapping
    public List<Shop> selectAll() {
        return shopService.selectAll();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("{id}")
    public Shop selectOne(@ApiParam(value = "ID属性", required = true) @PathVariable("id") Long id) {
        return shopService.selectOne(id);
    }

    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Shop shop) {
        if (null == shop.getId()) {
            shopService.add(shop);
        } else {
            shopService.update(shop);
        }
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        shopService.delete(id);
        return AjaxResult.success();
    }

    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids) {
        shopService.batchDel(ids);
        return AjaxResult.success();
    }

    @PostMapping
    public PageInfo<Shop> page(@RequestBody ShopQuery query) {
        return shopService.page(query);
    }
}
