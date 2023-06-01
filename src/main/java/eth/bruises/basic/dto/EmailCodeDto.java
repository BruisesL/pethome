package eth.bruises.basic.dto;

import eth.bruises.basic.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户邮箱注册发送邮箱验证码的接收实体
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailCodeDto {
    @NotBlank(message = "邮箱不可为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotBlank(message = "图形验证码不可为空")
    private String imageCode;
    @NotBlank(message = "参数异常")
    private String uuid;
}
