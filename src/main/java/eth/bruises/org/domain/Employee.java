package eth.bruises.org.domain;

import eth.bruises.basic.domain.BaseDomain;
import eth.bruises.basic.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 员工domain
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseDomain {
    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不可为空")
    private String username;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不可为空")
    @Phone(message = "手机号格式不正确")
    private String phone;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不可为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    @Min(value = 18, message = "不可为未成年")
    private Integer age;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 所属部门ID
     */
    private Long departmentId;
    /**
     * 登陆信息ID
     */
    private Long logininfoId;
    /**
     * 所属店铺ID
     */
    private Long shopId;
}
