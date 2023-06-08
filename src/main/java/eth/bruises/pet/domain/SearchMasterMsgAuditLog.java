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
public class SearchMasterMsgAuditLog extends BaseDomain{

    /**
     * 消息id
     */
    private Long msgId;
    /**
     * 状态 0失败 1成功
     */
    private Integer state;
    /**
     * 审核人 如果为null系统审核
     */
    private Long auditId;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 备注
     */
    private String note;

}
