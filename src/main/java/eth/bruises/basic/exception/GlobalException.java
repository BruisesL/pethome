package eth.bruises.basic.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *自定义业务异常
 *
 * @author bruises
 */
@Data
public class GlobalException extends RuntimeException{

    private String code;

    private String globalMessage;

    public GlobalException() {
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String code,String message) {
        super(message);
        this.code = code;
        this.globalMessage = message;
    }

    public GlobalException(GlobalExceptionEnum globalExceptionEnum) {
        super(globalExceptionEnum.getMessage());
        this.code = globalExceptionEnum.getCode();
        this.globalMessage = globalExceptionEnum.getMessage();
    }
}
