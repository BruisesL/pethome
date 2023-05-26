package eth.bruises.sys.controller;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import eth.bruises.sys.domain.SystemDictionaryType;
import eth.bruises.sys.query.SystemDictionaryTypeQuery;
import eth.bruises.sys.service.ISystemDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典类型表的controller层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/sdt")
public class SystemDictionaryTypeController {
    @Autowired
    private ISystemDictionaryTypeService service;

    @GetMapping
    public List<SystemDictionaryType> findAll() {
        return service.selectAll();
    }

    @PostMapping
    public PageInfo<SystemDictionaryType> findByCondition(@RequestBody SystemDictionaryTypeQuery query) {
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
    public AjaxResult addOrUpdate(@RequestBody SystemDictionaryType systemDictionaryType) {
        if (null == systemDictionaryType.getId()){
            service.add(systemDictionaryType);
        }
        else {
            service.update(systemDictionaryType);
        }
        return AjaxResult.success();
    }

}
