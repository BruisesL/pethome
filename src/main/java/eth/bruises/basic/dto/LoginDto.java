package eth.bruises.basic.dto;


import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 登陆信息的Dto
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "用户名不可为空")
    private String username;
    @NotBlank(message = "密码不可为空")
    private String password;
    @NotNull(message = "参数异常")
    private Integer type;
}
