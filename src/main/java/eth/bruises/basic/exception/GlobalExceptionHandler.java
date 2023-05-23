package eth.bruises.basic.exception;

import eth.bruises.basic.utils.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 * RestControllerAdvice注解 是在controller层捕获异常
 * @author bruises
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局业务异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public AjaxResult globalException(GlobalException e) {
        e.printStackTrace();
        return AjaxResult.error(e.getCode(), e.getGlobalMessage());
    }

    /**
     * 全类型异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult exception(Exception e) {
        e.printStackTrace();
        return AjaxResult.error(GlobalExceptionEnum.ERROR.getCode(),GlobalExceptionEnum.ERROR.getMessage());
    }

}
