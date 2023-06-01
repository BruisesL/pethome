package eth.bruises.basic.dto;

import eth.bruises.basic.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户手机注册发送手机验证码的接收实体
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCodeDto {
    @NotBlank(message = "手机号不可为空")
    @Phone(message = "手机号格式不正确")
    private String phone;
    @NotBlank(message = "图形验证码不可为空")
    private String imageCode;
    @NotBlank(message = "参数异常")
    private String uuid;
}
