package eth.bruises.basic.annotation;

import java.lang.annotation.*;

/**
 * 修饰注解的注解有四大源注解
 *
 * @author bruises
 */
@Target(ElementType.METHOD) // 表示此注解作用于哪个地方
@Retention(RetentionPolicy.RUNTIME) // 此注解在那个时期有效
public @interface PreAuthorize {

    String name();
    String sn();

}
