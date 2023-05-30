package eth.bruises.org.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接收查询出的店铺状态统计数据
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopStatisticsBo {

    /**
     * 店铺状态
     */
    private Integer state;

    /**
     * 状态对应数据个数
     */
    private Integer count;

}
