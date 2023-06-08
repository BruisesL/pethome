package eth.bruises.basic.config;

import eth.bruises.basic.annotation.PreAuthorize;
import eth.bruises.basic.utils.JwtUtil;
import eth.bruises.user.vo.PayloadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器
 *
 * @author bruises
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否有token，如果没有，则给前端返回异常，不放行
        String token = request.getHeader("token");
        // 判断token是否存在
        if (!StringUtils.isEmpty(token) && JwtUtil.verify(token)) {
            PayloadVo payloadVo = JwtUtil.parseToken(token);
            List<String> permissions = payloadVo.getPermissions();
            // 获取请求路径
            if (handler instanceof HandlerMethod) {
                PreAuthorize methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PreAuthorize.class);
                if (methodAnnotation != null) {
                    String sn = methodAnnotation.sn();
                    if (!permissions.contains(sn)) {
                        // 设置响应格式
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write("{\"success\":false, \"message\":\"NoLogin\", \"data\":null}");
                        return false;
                    }
                }
            }
            return true;
        }
        // 设置响应格式
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"success\":false, \"message\":\"NoLogin\", \"data\":null}");
        return false;

    }
}
