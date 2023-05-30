package eth.bruises.basic.abandoned;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 通过ApplicationContextAware加载Spring上下文环境(获取bean)
 *
 * @author bruises
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        SpringBeanUtil.applicationContext = applicationContext;
//    }

    /**
     * 通过名称在spring容器中获取对象
     *
     * @param  clazz
     * @return
     */
//    public static <T> T getBean(Class<T> clazz) {
//        return applicationContext.getBean(clazz);
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    /**
     * 通过名称在spring容器中获取对象
     */
    public static ApplicationContext getContext() {
        return applicationContext;
    }
}  