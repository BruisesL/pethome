package eth.bruises.basic.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 绑定微信登陆第一次获取的code与第二次获取的token与openid
 * 后续通过code在redis查Bo实体
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxLoginBo {

    private String accessToken;
    private String openid;
}
