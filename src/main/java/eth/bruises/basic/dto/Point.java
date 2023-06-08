package eth.bruises.basic.dto;

import lombok.Data;

/**
 * 存放经纬度
 * @author bruises
 */
@Data
public class Point {
    /**
     * 经度
     */
    private Double lng;
    /**
     * 维度
     */
    private Double lat;
}
