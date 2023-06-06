package eth.bruises.sys.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class Role extends BaseDomain{

    private String name;
    private String sn;

    private List<Long> menus;

    private List<Long> permissions;

}
