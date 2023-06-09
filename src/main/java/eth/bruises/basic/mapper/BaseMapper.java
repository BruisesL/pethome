package eth.bruises.basic.mapper;

import eth.bruises.basic.query.BaseQuery;

import java.util.List;

/**
 * BaseMapper
 * @author bruises
 */
public interface BaseMapper<T> {
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
    int add(T t);

    /**
     * 修改
     * @param t
     * @return int
     */

    int update(T t);

    /**
     * 删除
     * @param id
     * @return int
     */
    int delete(Long id);

    /**
     * 批量删除
     * @param ids
     * @return int
     */
    int batchDel(List<Long> ids);

    /**
     * 分页查询的数据条数
     *
     * @param query
     * @return
     */
    Integer queryByCount(BaseQuery query);

    /**
     * 分页查询数据
     *
     * @param query
     * @return
     */
    List<T> queryByPage(BaseQuery query);
}
