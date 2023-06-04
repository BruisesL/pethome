package eth.bruises.basic.service;

import eth.bruises.basic.dto.LoginDto;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.vo.LoginVo;

/**
 * 登陆校验的接口
 *
 * @author bruises
 */
public interface ILoginService {
    /**
     * 登陆校验的方法
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);

    /**
     * 安全退出的方法
     * @param token
     */
    void logout(String token);

    /**
     * 微信登陆的方法
     * @param code
     * @return
     */
    AjaxResult wxLogin(String code);
}
