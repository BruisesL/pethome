package eth.bruises.org.domain;


import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "店铺名称", orderNum = "1", width = 30)
    @NotBlank(message = "店铺名不可为空")
    private String name;
    /**
     * 店铺座机
     */
    @Excel(name = "店铺电话", orderNum = "2", width = 30)
    private String tel;
    /**
     * 入驻时间
     */
    @Excel(name = "入驻时间", orderNum = "3", width = 30, exportFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date registerTime = new Date();
    /**
     * 店铺状态：1待审核，2审核通过待激活，3激活成功，4驳回
     */
    @Excel(name = "店铺状态", orderNum = "4", width = 30, replace = {"待审核_1", "待激活_2", "已激活_3", "驳回_4"})
    private Integer state;
    /**
     * 店铺地址
     */
    @Excel(name = "店铺地址", orderNum = "5", width = 30)
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
