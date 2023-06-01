package eth.bruises.user.service;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.user.domain.User;
import eth.bruises.basic.service.BaseService;
import eth.bruises.user.dto.EmailRegisterDto;
import eth.bruises.user.dto.PhoneRegisterDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bruises
 * @since 2023-05-31
 */
public interface IUserService extends BaseService<User> {
    /**
     * 手机号注册
     * @param dto
     * @return
     */
    void phoneRegister(PhoneRegisterDto dto);

    /**
     * 邮箱注册
     * @param dto
     * @return
     */
    void emailRegister(EmailRegisterDto dto);
}
