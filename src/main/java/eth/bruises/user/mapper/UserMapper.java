package eth.bruises.user.mapper;

import eth.bruises.user.domain.User;
import eth.bruises.basic.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bruises
 * @since 2023-05-31
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 通过邮箱查找用户
     * @param email
     * @return
     */
    User findByEmail(String email);
}
