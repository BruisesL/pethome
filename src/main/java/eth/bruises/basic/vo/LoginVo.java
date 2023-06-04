package eth.bruises.basic.vo;


import eth.bruises.user.domain.Logininfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登陆信息返回实体
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private String token;
    private Logininfo loginInfo;
}
