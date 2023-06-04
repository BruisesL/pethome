package eth.bruises.basic.constants;

/**
 * 微信登陆相关常量
 *
 * @author bruises
 */
public class WxConstants {
    public static final String APPID = "wxd853562a0548a7d0";
    public static final String SECRET = "4a5d5615f93f24bdba2ba8534642dbb6";
    public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    public static String GET_USER_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
}