package eth.bruises.sys.controller;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import eth.bruises.sys.domain.SystemDictionaryDetail;
import eth.bruises.sys.query.SystemDictionaryDetailQuery;
import eth.bruises.sys.service.ISystemDictionaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典明细表的controller层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/sdd")
public class SystemDictionaryDetailController {
    @Autowired
    private ISystemDictionaryDetailService service;

    @GetMapping
    public List<SystemDictionaryDetail> findAll() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public List<SystemDictionaryDetail> findByDept(@PathVariable("id") Long id) {
        return service.findBySystemDictionaryTypeId(id);
    }

    @PostMapping
    public PageInfo<SystemDictionaryDetail> findByCondition(@RequestBody SystemDictionaryDetailQuery query) {
        return service.page(query);
    }

    @DeleteMapping("/{id}")
    public AjaxResult delById(@PathVariable("id") Long id) {
        service.delete(id);
        return AjaxResult.success();
    }

    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids) {
        service.batchDel(ids);
        return AjaxResult.success();
    }

    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody SystemDictionaryDetail systemDictionaryDetail) {
        if (null == systemDictionaryDetail.getId()){
            service.add(systemDictionaryDetail);
        }
        else {
            service.update(systemDictionaryDetail);
        }
        return AjaxResult.success();
    }

}
