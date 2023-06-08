package eth.bruises.pet.domain;

import java.math.BigDecimal;
import java.util.Date;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 实体类：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchMasterMsg extends BaseDomain{

    @NotBlank(message = "标题不可为空")
    private String title;
    /**
     * 0 待审核 1 审核通过 -1 驳回
     */
    private Integer state;
    /**
     * 宠物名称
     */
    @NotBlank(message = "宠物名不可为空")
    private String name;
    @NotNull(message = "年龄不可为空")
    private Integer age;
    @NotNull(message = "性别不可为空")
    private Boolean gender;
    /**
     * 毛色
     */
    @NotBlank(message = "毛色不可为空")
    private String coatColor;
    /**
     * fastdfs地址1,fastdfs地址1
     */
    private String resources;
    /**
     * 类型
     */
    private Long petType;
    @NotNull(message = "价格不可为空")
    private BigDecimal price;
    @NotBlank(message = "地址不可为空")
    private String address;
    private Long userId;
    /**
     * 店铺id 消息分配的店铺
     */
    private Long shopId;
    private String note;


}
