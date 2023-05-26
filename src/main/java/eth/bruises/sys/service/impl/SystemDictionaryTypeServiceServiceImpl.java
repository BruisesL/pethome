package eth.bruises.sys.service.impl;

import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.sys.domain.SystemDictionaryType;
import eth.bruises.sys.mapper.SystemDictionaryDetailMapper;
import eth.bruises.sys.service.ISystemDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 数据字典明细表的service层实现类
 *
 * @author bruises
 */
@Service
public class SystemDictionaryTypeServiceServiceImpl extends BaseServiceImpl<SystemDictionaryType> implements ISystemDictionaryTypeService {
    @Autowired
    private SystemDictionaryDetailMapper mapper;

    @Override
    public void delete(Long id) {
        mapper.delByTypesId(id);
        super.delete(id);
    }

    @Override
    public void batchDel(List<Long> ids) {
        mapper.delPatchByTypesId(ids);
        super.batchDel(ids);
    }
}
