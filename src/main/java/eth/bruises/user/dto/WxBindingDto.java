package eth.bruises.user.dto;

import eth.bruises.basic.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 微信绑定的Dto
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxBindingDto {
    @NotBlank(message = "手机号不能为空！")
    @Phone(message = "手机号格式错误")
    private String phone;

    @NotBlank(message = "手机验证码不能为空！")
    private String phoneCode;

    @NotBlank(message = "参数异常！")
    private String code;

}
