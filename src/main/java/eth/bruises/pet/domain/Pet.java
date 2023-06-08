package eth.bruises.pet.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends BaseDomain{

    private String name;
    private BigDecimal costprice;
    private BigDecimal saleprice;
    /**
     * 类型id
     */
    private Long typeId;
    private String resources;
    /**
     * 状态：0下架 1上架
     */
    private Integer state;
    private Date offsaletime;
    private Date onsaletime;
    private Date createtime;
    /**
     * 店铺Id 如果被领养店铺id为null
     */
    private Long shopId;
    /**
     * 如果被领养为领养用户id
     */
    private Long userId;
    private Long searchMasterMsgId;

}
