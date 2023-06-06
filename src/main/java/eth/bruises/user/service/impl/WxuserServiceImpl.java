package eth.bruises.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import eth.bruises.basic.bo.WxLoginBo;
import eth.bruises.basic.constants.WxConstants;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.vo.LoginVo;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.domain.User;
import eth.bruises.user.domain.Wxuser;
import eth.bruises.user.dto.WxBindingDto;
import eth.bruises.user.mapper.LogininfoMapper;
import eth.bruises.user.mapper.UserMapper;
import eth.bruises.user.mapper.WxuserMapper;
import eth.bruises.user.service.IWxuserService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 业务实现类：
 */
@Service
public class WxuserServiceImpl extends BaseServiceImpl<Wxuser> implements IWxuserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WxuserMapper wxuserMapper;

    @Autowired
    private LogininfoMapper logininfoMapper;

    public static String WXPHONE_CODE_KEY = "wx_register:%s";


    @Override
    public LoginVo wxBinding(WxBindingDto wxBindingDto) {
        String code = wxBindingDto.getCode();
        String phone = wxBindingDto.getPhone();
        String phoneCode = wxBindingDto.getPhoneCode();
        // 校验验证码是否正确
        String key = String.format(WXPHONE_CODE_KEY, phone);
        Object codeObj = redisTemplate.opsForValue().get(key);
        if (codeObj == null) {
            throw new GlobalException(GlobalExceptionEnum.VERIFICATION_CODE_HAS_EXPIRED);
        }
        if (!codeObj.toString().equals(phoneCode)) {
            throw new GlobalException(GlobalExceptionEnum.VERIFICATION_CODE_ERROR);
        }
        // 使用code获取到在第二步中存储的token&openid，如果没有抛出异常
        Object obj = redisTemplate.opsForValue().get(code);
        if (obj == null) {
            throw new GlobalException(GlobalExceptionEnum.WECHAT_LOGIN_HAS_EXPIRED);
        }
        // 判断该帐号是否存在，如果存在那么是否已经绑定微信，如果已经绑定那么提示已存在
        User user = userMapper.findByPhone(phone);
        if (user != null) {
            Long userId = user.getId();
            // user帐号存在，并且也绑定过微信帐号说明帐号已被绑定了
            Wxuser wxuser = wxuserMapper.findByUserId(userId);
            if (wxuser != null) {
                throw new GlobalException(GlobalExceptionEnum.ERROR);
            }
        }
        // 对于此Openid所对应的WXUser用户存在时的处理
        WxLoginBo wxLoginBo = JSONObject.parseObject(JSONObject.toJSONString(obj), WxLoginBo.class);
        String openid = wxLoginBo.getOpenid();
        Wxuser wxuser = wxuserMapper.findByOpenid(openid);
        // 此微信用户存在，并且所绑定的user用户也存在，那么只能抛出异常
        if (wxuser != null) {
            Long userId = wxuser.getUserId();
            if (userMapper.selectOne(userId) != null) {
                throw new GlobalException(GlobalExceptionEnum.ERROR);
            }
        } else {
            // wxUser==null
            wxuser = new Wxuser();
            String url = String.format(WxConstants.GET_USER_URL, wxLoginBo.getAccessToken(), openid);
            String wechatInfoJsonStr = HttpUtil.get(url);
            if (wechatInfoJsonStr.indexOf("error") != -1) {
                throw new GlobalException(GlobalExceptionEnum.ERROR);
            }
            JSONObject jsonObject = JSONObject.parseObject(wechatInfoJsonStr);
            wxuser.setOpenid(openid);
            wxuser.setUnionid(jsonObject.get("unionid").toString());
            wxuser.setNickname(jsonObject.get("nickname").toString());
            wxuser.setHeadimgurl(jsonObject.get("headimgurl").toString());
        }
        // wxUser存在
        // 新增或者修改wxuser，并新增user和logininfo
        Logininfo logininfo = new Logininfo();
        Long userId = saveUserAndLogininfo(phone, logininfo);
        wxuser.setUserId(userId);
        if (wxuser.getId() != null) {
            wxuserMapper.update(wxuser);
        } else {
            wxuserMapper.add(wxuser);
        }
        // 免密登录
        // 走登录逻辑
        // 生成UUID->Token
        UUID uuid = UUID.randomUUID();
        // Kye是UUID，Value是用户信息存入到Redis中设置30分钟有效期
        redisTemplate.opsForValue().set(uuid.toString(), logininfo, 30, TimeUnit.MINUTES);
        // 将token和用户信息放入到vo中并响应
        logininfo.setPassword("");
        logininfo.setSalt("");
        return new LoginVo(uuid.toString(), logininfo);
    }

    private Long saveUserAndLogininfo(String phone, Logininfo logininfo) {
        User user = new User();
        user.setUsername(phone);
        user.setPhone(phone);
        String salt = RandomUtil.randomString(32);
        user.setSalt(salt);
        user.setPassword(SecureUtil.md5(salt + "123456"));
        // 发送短信给用户，初始化密码为123456
//        SendSmsUtil.sendSms(dto.getPhone(), "已为您初始化密码为：123456");
        System.out.println("已为您初始化密码为：123456");
        user.setState(1);
        BeanUtil.copyProperties(user, logininfo);
        logininfo.setDisable(true);
        logininfo.setType(1);
        logininfoMapper.add(logininfo);
        user.setLogininfoId(logininfo.getId());
        userMapper.add(user);
        return user.getId();
    }
}
