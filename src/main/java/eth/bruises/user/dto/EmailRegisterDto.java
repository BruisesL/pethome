package eth.bruises.user.dto;

import eth.bruises.basic.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户邮箱注册的Dto
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRegisterDto {
    @NotBlank(message = "邮箱不可为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String imageCode;
    @NotBlank(message = "邮箱验证码不可为空")
    private String emailCode;
    @NotBlank(message = "密码不可为空")
    private String password;
    @NotBlank(message = "确认密码不可为空")
    private String confirmPassword;

}
