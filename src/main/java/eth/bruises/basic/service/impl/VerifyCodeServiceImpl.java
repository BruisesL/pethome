package eth.bruises.basic.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import eth.bruises.basic.dto.EmailCodeDto;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.service.IVerifyCodeService;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.dto.PhoneCodeDto;
import eth.bruises.basic.utils.SendEmailUtil;
import eth.bruises.basic.utils.SendSmsUtil;
import eth.bruises.user.domain.User;
import eth.bruises.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 验证码service的实现类
 *
 * @author bruises
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class VerifyCodeServiceImpl implements IVerifyCodeService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    private static String REGISTER_CODE_KEY = "register:%s";
    public static String REGISTER_WXPHONE_CODE_KEY = "wx_register:%s";


    /**
     * 生成图形验证码的base64值
     *
     * @param uuid
     * @return
     */
    @Override
    public String imageCode(String uuid) {
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(115, 40, 4, 4);
        String code = shearCaptcha.getCode();
        String imageBase64 = shearCaptcha.getImageBase64();
        redisTemplate.opsForValue().set(uuid, code, 3600000, TimeUnit.MILLISECONDS);
        return imageBase64;
    }

    /**
     * 发送手机验证码的方法
     *
     * @param dto
     */
    @Override
    public AjaxResult phoneCode(PhoneCodeDto dto) {

        // 业务校验
        // 校验图形验证码是否过期
        Object imgCode = redisTemplate.opsForValue().get(dto.getUuid());
        if (null == imgCode) {
            throw new GlobalException(GlobalExceptionEnum.GRAPHIC_VERIFICATION_HAS_EXPIRED);
        }
        // 图形验证码是否正确
        if (!StrUtil.equalsAnyIgnoreCase(imgCode.toString(), dto.getImageCode())) {
            throw new GlobalException(GlobalExceptionEnum.GRAPHIC_VERIFICATION_ERROR);
        }
        // 校验手机号是否存在
        User user = userMapper.findByPhone(dto.getPhone());
        if (null != user) {
            throw new GlobalException(GlobalExceptionEnum.USER_EXISTS);
        }
        // 业务逻辑
        // 判断是否是一分钟以内发送的
        String phoneKey = String.format(REGISTER_CODE_KEY, dto.getPhone());
        String code = getCode(phoneKey);
        // 发短信
//        SendSmsUtil.sendSms(dto.getPhone(), "您的验证码是：" + code);
        System.out.println("您的验证码是：" + code);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult emailCode(EmailCodeDto dto) {

        // 业务校验
        // 校验图形验证码是否过期
        Object imgCode = redisTemplate.opsForValue().get(dto.getUuid());
        if (null == imgCode) {
            throw new GlobalException(GlobalExceptionEnum.GRAPHIC_VERIFICATION_HAS_EXPIRED);
        }
        // 图形验证码是否正确
        if (!StrUtil.equalsAnyIgnoreCase(imgCode.toString(), dto.getImageCode())) {
            throw new GlobalException(GlobalExceptionEnum.GRAPHIC_VERIFICATION_ERROR);
        }
        // 校验手机号是否存在
        User user = userMapper.findByEmail(dto.getEmail());
        if (null != user) {
            throw new GlobalException(GlobalExceptionEnum.USER_EXISTS);
        }
        // 业务逻辑
        // 判断是否是一分钟以内发送的
        String emailKey = String.format(REGISTER_CODE_KEY, dto.getEmail());
        String code = getCode(emailKey);

        // 发邮件
        SendEmailUtil.sendMail(dto.getEmail(), "邮箱验证码接收", "您的验证码是：" + code);
//        SendSmsUtil.sendSms(dto.getPhone(), "您的验证码是：" + code);
        System.out.println("您的验证码是：" + code);
        return AjaxResult.success();
    }



    @Override
    public AjaxResult wxPhoneCode(String phone) {
        // 拼接redis存储的业务键的key
        String key = String.format(REGISTER_WXPHONE_CODE_KEY, phone);
        String code = getCode(key);
        // 发短信
//        SendSmsUtil.sendSms(dto.getPhone(), "您的验证码是：" + code);
        System.out.println("您的验证码是：" + code);
        return AjaxResult.success();
    }

    /**
     * 抽取方法，判断key是否已在redis内有有效值，返回验证码
     * @param key
     * @return
     */
    private String getCode(String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        String code = "";
        if (expire == null || expire < 240000) {
            // 生成邮件验证码
            code = RandomUtil.randomString(6);
        } else {
            throw new GlobalException(GlobalExceptionEnum.MESSAGE_SEND_ERROR);
        }
        // 存redis
        redisTemplate.opsForValue().set(key, code, 300000, TimeUnit.MILLISECONDS);
        return code;
    }
}
