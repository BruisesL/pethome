package eth.bruises.basic.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号格式校验注解
 *
 * @author bruises
 */
@Target( ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 此处指定了校验规则器
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {
    //SpringValidator必要的三个属性
    String message() default "无效的手机格式";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}