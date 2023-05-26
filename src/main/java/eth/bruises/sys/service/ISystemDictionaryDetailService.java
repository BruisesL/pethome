package eth.bruises.sys.service;

import eth.bruises.basic.service.BaseService;
import eth.bruises.sys.domain.SystemDictionaryDetail;

import java.util.List;

/**
 * 数据字典明细表的service层接口，用于定义业务方法
 *
 * @author bruises
 */
public interface ISystemDictionaryDetailService extends BaseService<SystemDictionaryDetail> {
    /**
     * 查询数据字典类型下的所有明细数据
     * @param id
     * @return
     */
    List<SystemDictionaryDetail> findBySystemDictionaryTypeId(Long id);

}
