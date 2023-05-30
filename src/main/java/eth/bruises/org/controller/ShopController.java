package eth.bruises.org.controller;

import eth.bruises.basic.utils.ExcelUtils;
import eth.bruises.org.dto.ShopAuditLogDto;
import eth.bruises.org.service.IShopService;
import eth.bruises.org.domain.Shop;
import eth.bruises.org.query.ShopQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    /**
     * 审核拒绝
     * @param dto
     * @return
     */
    @PostMapping("/audit/reject")
    public AjaxResult reject(@Valid @RequestBody ShopAuditLogDto dto) {
        shopService.reject(dto);
        return AjaxResult.success();
    }

    /**
     * 审核通过
     * @param dto
     * @return
     */
    @PostMapping("/audit/pass")
    public AjaxResult pass(@Valid @RequestBody ShopAuditLogDto dto) {
        shopService.pass(dto);
        return AjaxResult.success();
    }

    /**
     * 店铺激活
     * @param shopId
     * @return
     */
    @GetMapping("/active/{shopId}")
    public AjaxResult active(@PathVariable("shopId")Long shopId) {
        shopService.active(shopId);
        return AjaxResult.success();
    }

    /**
     * excel导出 直接将生成的excel给浏览器下载
     */
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        List<Shop> shops = shopService.selectAll();
        ExcelUtils.exportExcel(shops, null, "店铺信息", Shop.class, "店铺信息.xlsx", true, response);
    }

    @PostMapping("/import")
    public AjaxResult importExcel(@RequestPart MultipartFile file) {
        // titleRows：占用了多少行
        List<Shop> shops = ExcelUtils.importExcel(file, 1, 1, Shop.class);
        shopService.batchAdd(shops);
        return AjaxResult.success();
    }


}
