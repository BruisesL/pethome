package eth.bruises.org.domain;

import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工domain
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseDomain {
    private String username;
    private String phone;
    private String email;
    private String salt;
    private String password;
    private String age;
    private String state;
    private String departmentId;
    private String logininfoId;
    private String shopId;
}
