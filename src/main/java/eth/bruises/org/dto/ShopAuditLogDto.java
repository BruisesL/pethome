package eth.bruises.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 店铺审核日志Dto
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopAuditLogDto {
    /**
     * 店铺ID
     */
    @NotNull(message = "店铺Id不能为空！")
    private Long shopId;
    /**
     * 审批备注
     */
    @NotBlank(message = "请输入备注！")
    private String note;
}
