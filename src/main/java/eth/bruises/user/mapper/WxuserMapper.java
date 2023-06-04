package eth.bruises.user.mapper;

import eth.bruises.user.domain.Wxuser;
import eth.bruises.basic.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bruises
 * @since 2023-06-03
 */
public interface WxuserMapper extends BaseMapper<Wxuser> {
    /**
     * 通过openid查询微信用户
     * @param string
     * @return
     */
    Wxuser findByOpenid(String string);

    /**
     * 通过userId查询微信用户
     * @param userId
     * @return
     */
    Wxuser findByUserId(Long userId);
}
