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
    ERROR("-1","系统繁忙，请稍后重试！")
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
