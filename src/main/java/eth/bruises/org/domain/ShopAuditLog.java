package eth.bruises.org.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopAuditLog extends BaseDomain{

    private Integer state;
    private Long shopId;
    private Long auditId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date auditTime = new Date();
    private String note;


}
