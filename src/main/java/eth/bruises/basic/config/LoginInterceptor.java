package eth.bruises.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器
 *
 * @author bruises
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否有token，如果没有，则给前端返回异常，不放行
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            Object logininfo = redisTemplate.opsForValue().get(token);
            if (logininfo != null) {
                // 如果有则更新redis时间,并放行
                redisTemplate.expire(token, 30, TimeUnit.MINUTES);
                return true;
            }
        }
        // 设置响应格式
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"success\":false, \"message\":\"NoLogin\", \"data\":null}");
        return false;

    }
}
