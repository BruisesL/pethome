package eth.bruises.org.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * echarts报表的shop状态统计数据
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopStatisticsVo {

    /**
     * 店铺状态集合
     */
    private List<Integer> states;

    /**
     * 状态对应数据个数集合
     */
    private List<Integer> counts;

}
