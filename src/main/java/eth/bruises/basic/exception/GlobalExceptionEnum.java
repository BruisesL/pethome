package eth.bruises.basic.exception;

import lombok.Getter;

/**
 * 全局异常错误枚举
 *
 * @author bruises
 */
@Getter
public enum GlobalExceptionEnum {

    // 公共成功枚举
    SUCCESS("0","操作成功！"),
    // 公共失败枚举
    ERROR("-1","系统繁忙，请稍后重试！"),
    // 参数校验异常
    PARAM_ERROR("1001", "参数校验异常！"),
    SHOP_EXISTS("1002", "店铺已存在！"),
    SHOP_ADMIN_EXISTS("1003", "管理员已存在！"),
    TEXT_CENSOR_ERROR("1004", "文字包含敏感字符，请修改！"),
    IMG_CENSOR_ERROR("1005", "图片不合规，请修改！"),

    SHOP_ADMIN_NOT_EXIST("1006", "店铺不存在！"),
    SHOP_NOT_EXIST("1007", "店铺未查询到管理员不存在！"),
    SHOP_STATE_ERROR("1008", "店铺状态异常！"),
    GRAPHIC_VERIFICATION_HAS_EXPIRED("1009", "图形验证码已过期！"),
    GRAPHIC_VERIFICATION_ERROR("1010", "图形验证码错误！"),
    USER_EXISTS("1011", "用户已存在！"),
    MESSAGE_SEND_ERROR("1012", "请勿重复发送短信！"),
    VERIFICATION_CODE_HAS_EXPIRED("1013", "验证码已过期！"),
    VERIFICATION_CODE_ERROR("1014", "验证码错误！"),
    TWO_PASSWORDS_NOT_MATCH("1015", "两次密码不匹配！"),
    INCORRECT_USERNAME_OR_PASSWORD("1016","账号或密码错误"),
    ACCOUNT_DISABLED("1017","账号已禁用"),
    WECHAT_LOGIN_HAS_EXPIRED("1009", "微信登陆已过期，请重新扫码！"),


    ;


    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 有参构造
     * @param code
     * @param message
     */
    private GlobalExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
