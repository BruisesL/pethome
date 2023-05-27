package eth.bruises.org.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

/**
 * 实体类：
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends BaseDomain {

    /**
     * 店铺名称
     */
    @NotBlank(message = "店铺名不可为空")
    private String name;
    /**
     * 店铺座机
     */
    private String tel;
    /**
     * 入驻时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerTime = new Date();
    /**
     * 店铺状态：1待审核，2审核通过待激活，3激活成功，4驳回
     */
    private Integer state;
    /**
     * 店铺地址
     */
    @NotBlank(message = "店铺地址不可为空")
    private String address;
    /**
     * 店铺logo
     */
    private String logo;
    /**
     * 店铺管理员ID
     */
    private Long adminId;
    /**
     * 店铺管理员
     */
    private Employee admin;
}
