package eth.bruises.sys.mapper;

import eth.bruises.basic.mapper.BaseMapper;
import eth.bruises.sys.domain.SystemDictionaryDetail;

import java.util.List;

/**
 * 数据字典明细表mapper层接口，用于定义查询方法
 *
 * @author bruises
 */
public interface SystemDictionaryDetailMapper extends BaseMapper<SystemDictionaryDetail> {
    /**
     * 按类型删除明细
     * @param id
     */
    void delByTypesId(Long id);

    /**
     * 按类型批量删除
     * @param ids
     */
    void delPatchByTypesId(List<Long> ids);
}
