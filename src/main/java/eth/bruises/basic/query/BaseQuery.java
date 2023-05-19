package eth.bruises.basic.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询基础条件
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuery {
    /**
     * 当前页码
     */
    private Integer currentPage = 1;
    /**
     * 每页数据条数
     */
    private Integer pageSize = 5;
    /**
     * 查询关键字
     */
    private String keyword;

    /**
     * 计算数据起始Begin
     * @return
     */
    public Integer getBegin() {
        return (currentPage - 1) * pageSize;
    }
}
