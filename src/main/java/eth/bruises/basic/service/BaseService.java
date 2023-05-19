package eth.bruises.basic.service;

import eth.bruises.basic.query.BaseQuery;
import eth.bruises.basic.utils.PageInfo;

import java.util.List;

/**
 * BaseService
 * @author bruises
 */
public interface BaseService<T> {
    /**
     * 查询所有的方法
     * @return
     */
    List<T> selectAll();

    /**
     * 按id查询
     * @param id
     * @return
     */
    T selectOne(Long id);

    /**
     * 添加
     * @param t
     */
    void add(T t);

    /**
     * 修改
     * @param t
     */
    void update(T t);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDel(List<Long> ids);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageInfo<T> page(BaseQuery query);
}
