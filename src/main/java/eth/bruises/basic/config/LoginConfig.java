package eth.bruises.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器规则配置
 *
 * @author bruises
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/logout", "/wxLogin/**", "/verify/**",
                        "/shop/settlement", "/shop/active/**",
                        "/fastdfs/**", "/wxLogin/**", "/wxuser/wxBinding/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
