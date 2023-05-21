package eth.bruises.basic.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询结果
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {
    /**
     * 查询总条数
     */
    private Integer total;

    /**
     * 查询数据
     */
    private List<T> data = new ArrayList<>();
}
