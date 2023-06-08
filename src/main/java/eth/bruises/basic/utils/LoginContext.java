package eth.bruises.basic.utils;

import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.vo.PayloadVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆的上下文信息
 * @author bruises
 */
public class LoginContext {
    public static Logininfo getLogininfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        PayloadVo payloadVo = JwtUtil.parseToken(token);
        return payloadVo.getLogininfo();
    }
}
