package eth.bruises.basic.service.impl;

import eth.bruises.basic.mapper.BaseMapper;
import eth.bruises.basic.query.BaseQuery;
import eth.bruises.basic.service.BaseService;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseService的实现类
 *
 * @author bruises
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    public T selectOne(Long id) {
        return baseMapper.selectOne(id);
    }

    @Transactional
    @Override
    public void add(T t) {
        baseMapper.add(t);
    }

    @Transactional
    @Override
    public void update(T t) {
        baseMapper.update(t);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        baseMapper.delete(id);
    }

    @Transactional
    @Override
    public void batchDel(List<Long> ids) {
        baseMapper.batchDel(ids);
    }

    @Override
    public PageInfo<T> page(BaseQuery query) {
        Integer totals = baseMapper.queryByCount(query);
        // 如果查询条数为0，就不继续查询，直接返回结果
        if (totals == 0) {
            return new PageInfo<>(0, new ArrayList<>());
        }

        List<T> data = baseMapper.queryByPage(query);
        return new PageInfo<>(totals, data);
    }
}
