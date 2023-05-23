package eth.bruises.basic.exception;

import eth.bruises.basic.utils.AjaxResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 处理domain中JSP303参数校验异常捕获的信息处理
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult exception(MethodArgumentNotValidException e) {
        e.printStackTrace();
        // ((BeanPropertyBindingResult) e.bindingResult).errors.get(0).defaultMessage
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // map()：对于流的一些中间业务操作就用此方法
        // ObjectError::getDefaultMessage：我只要流中对象的defaultMessage
        //errors.stream().map(o->o.getDefaultMessage())
        // Collectors.joining(",")：joining只对字符串生效，用其他类型会报错,把所有的得到的字符串使用逗号进行隔开并组合成一个字符串
        String messages = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
        return AjaxResult.error(GlobalExceptionEnum.PARAM_ERROR.getCode(), messages);
    }

    /**
     * 处理在controller层中，对传入参数进行JSP303参数校验时，异常捕获的信息处理
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult exception(ConstraintViolationException e) {
        e.printStackTrace();
        return AjaxResult.error(GlobalExceptionEnum.PARAM_ERROR.getCode(), e.getMessage());
    }



}
