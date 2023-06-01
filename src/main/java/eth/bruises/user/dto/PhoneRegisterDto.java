package eth.bruises.user.dto;

import eth.bruises.basic.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户手机注册的Dto
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRegisterDto {
    @NotBlank(message = "手机号不可为空")
    @Phone(message = "手机号格式不正确")
    private String phone;
    private String imageCode;
    @NotBlank(message = "手机验证码不可为空")
    private String phoneCode;
    @NotBlank(message = "密码不可为空")
    private String password;
    @NotBlank(message = "确认密码不可为空")
    private String confirmPassword;
}
