package eth.bruises.sys.service.impl;

import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.sys.domain.SystemDictionaryDetail;
import eth.bruises.sys.service.ISystemDictionaryDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典明细表的service层实现类
 *
 * @author bruises
 */
@Service
public class SystemDictionaryDetailServiceServiceImpl extends BaseServiceImpl<SystemDictionaryDetail> implements ISystemDictionaryDetailService {

    @Override
    public List<SystemDictionaryDetail> findBySystemDictionaryTypeId(Long id) {
        List<SystemDictionaryDetail> sdds = selectAll();
        return sdds.stream().filter(sdd -> id.equals(sdd.getTypesId())).collect(Collectors.toList());
    }
}
