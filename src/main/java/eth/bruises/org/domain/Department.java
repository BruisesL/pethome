package eth.bruises.org.domain;

import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门domain
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseDomain {
    private String sn;
    private String name;
    private String dirPath;
    private Integer state;
    private Long managerId;
    private Long parentId;
}
