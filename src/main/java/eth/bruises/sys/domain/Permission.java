package eth.bruises.sys.domain;

import java.math.BigDecimal;
import java.util.Date;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类：
 * @author bruises
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseDomain{

    private String name;
    private String url;
    private String descs;
    private String sn;

}
