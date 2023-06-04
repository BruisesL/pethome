package eth.bruises.user.service;

import eth.bruises.basic.vo.LoginVo;
import eth.bruises.user.domain.Wxuser;
import eth.bruises.basic.service.BaseService;
import eth.bruises.user.dto.WxBindingDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bruises
 * @since 2023-06-03
 */
public interface IWxuserService extends BaseService<Wxuser> {

    /**
     * 微信手机绑定
     *
     * @param wxBindingDto
     * @return LoginVo
     */
    LoginVo wxBinding(WxBindingDto wxBindingDto);
}
