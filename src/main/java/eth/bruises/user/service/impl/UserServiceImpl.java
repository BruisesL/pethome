package eth.bruises.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.domain.User;
import eth.bruises.user.dto.EmailRegisterDto;
import eth.bruises.user.dto.PhoneRegisterDto;
import eth.bruises.user.mapper.LogininfoMapper;
import eth.bruises.user.mapper.UserMapper;
import eth.bruises.user.service.IUserService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务实现类：
 *
 * @author bruises
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LogininfoMapper logininfoMapper;

    private static String REGISTER_CODE_KEY = "register:%s";

    @Transactional
    @Override
    public void add(User user) {
        copyAndSaveUserAndLogininfo(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        // 通过user.logininfoId查询logininfo对象
        Logininfo logininfo = logininfoMapper.selectOne(user.getLogininfoId());
        if (logininfo != null) {
            // copy参数到新的logininfo对象中
            Logininfo newLogininfo = copyUser(user);
            newLogininfo.setId(logininfo.getId());
            // 调用logininfo的更新方法
            logininfoMapper.update(newLogininfo);
        }
        super.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = userMapper.selectOne(id);
        if (user != null) {
            Long logininfoId = user.getLogininfoId();
            if (logininfoId != null) {
                logininfoMapper.delete(logininfoId);
            }
        }
        super.delete(id);
    }

    @Transactional
    @Override
    public void batchDel(List<Long> ids) {
        List<Long> logininfoIds = ids.stream()
                .map(id -> userMapper.selectOne(id))
                .map(User::getLogininfoId)
                .collect(Collectors.toList());
        logininfoMapper.batchDel(logininfoIds);
        super.batchDel(ids);
    }

    @Transactional
    @Override
    public void phoneRegister(PhoneRegisterDto dto) {

        synchronized (dto.getPhone()) {
            // 校验手机验证码是否正确
            String key = String.format(REGISTER_CODE_KEY, dto.getPhone());
            Object code = redisTemplate.opsForValue().get(key);
            if (null == code) {
                throw new GlobalException(GlobalExceptionEnum.VERIFICATION_CODE_HAS_EXPIRED);
            }
            if (!code.toString().equals(dto.getPhoneCode())) {
                throw new GlobalException(GlobalExceptionEnum.VERIFICATION_CODE_ERROR);
            }
            // 校验两次密码是否一致
            if (!dto.getPassword().equals(dto.getConfirmPassword())) {
                throw new GlobalException(GlobalExceptionEnum.TWO_PASSWORDS_NOT_MATCH);
            }
            // 校验手机号是否注册（加锁）
            User existUser = userMapper.findByPhone(dto.getPhone());
            if (null != existUser) {
                throw new GlobalException(GlobalExceptionEnum.USER_EXISTS);
            }
        }
        // 生成user
        User user = creatPhoneUser(dto);
        // 复制一份给logininfo
        // 取logininfoId，存user
        copyAndSaveUserAndLogininfo(user);
    }

    @Transactional
    @Override
    public void emailRegister(EmailRegisterDto dto) {

        synchronized (dto.getEmail()) {
            // 校验邮箱验证码是否正确
            String key = String.format(REGISTER_CODE_KEY, dto.getEmail());
            Object code = redisTemplate.opsForValue().get(key);
            if (null == code) {
                throw new GlobalException(GlobalExceptionEnum.VERIFICATION_CODE_HAS_EXPIRED);
            }
            if (!code.toString().equals(dto.getEmailCode())) {
                throw new GlobalException(GlobalExceptionEnum.VERIFICATION_CODE_ERROR);
            }
            // 校验两次密码是否一致
            if (!dto.getPassword().equals(dto.getConfirmPassword())) {
                throw new GlobalException(GlobalExceptionEnum.TWO_PASSWORDS_NOT_MATCH);
            }
            // 校验手机号是否注册（加锁）
            User existUser = userMapper.findByEmail(dto.getEmail());
            if (null != existUser) {
                throw new GlobalException(GlobalExceptionEnum.USER_EXISTS);
            }
        }
        // 生成user
        User user = creatEmailUser(dto);
        // 复制一份给logininfo
        // 取logininfoId，存user
        copyAndSaveUserAndLogininfo(user);
    }

    private User creatPhoneUser(PhoneRegisterDto dto) {
        User user = new User();
        user.setUsername(dto.getPhone());
        user.setPhone(dto.getPhone());
        String salt = RandomUtil.randomString(32);
        user.setSalt(salt);
        user.setPassword(SecureUtil.md5(salt + dto.getPassword()));
        user.setState(1);
        return user;
    }

    private User creatEmailUser(EmailRegisterDto dto) {
        User user = new User();
        user.setUsername(dto.getEmail());
        user.setEmail(dto.getEmail());
        String salt = RandomUtil.randomString(32);
        user.setSalt(salt);
        user.setPassword(SecureUtil.md5(salt + dto.getPassword()));
        user.setState(1);
        return user;
    }

    private void copyAndSaveUserAndLogininfo(User user) {
        Logininfo logininfo = new Logininfo();
        BeanUtils.copyProperties(user, logininfo);
        logininfo.setType(1);
        logininfo.setDisable(user.getState() == 1);
        logininfoMapper.add(logininfo);
        user.setLogininfoId(logininfo.getId());
        userMapper.add(user);
    }

    private Logininfo copyUser(User user) {
        Logininfo logininfo = new Logininfo();
        BeanUtils.copyProperties(user, logininfo);
        logininfo.setType(1);
        logininfo.setDisable(user.getState() == 1);
        return logininfo;
    }

}
