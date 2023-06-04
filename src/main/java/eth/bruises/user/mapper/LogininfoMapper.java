package eth.bruises.user.mapper;

import eth.bruises.basic.dto.LoginDto;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.basic.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bruises
 * @since 2023-05-31
 */
public interface LogininfoMapper extends BaseMapper<Logininfo> {
    /**
     * 查找账号是否存在
     * @param loginDto
     * @return
     */
    Logininfo findByAccount(LoginDto loginDto);
}
