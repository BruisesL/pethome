package eth.bruises.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import eth.bruises.basic.bo.WxLoginBo;
import eth.bruises.basic.constants.WxConstants;
import eth.bruises.basic.dto.LoginDto;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.service.ILoginService;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.vo.LoginVo;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.domain.User;
import eth.bruises.user.domain.Wxuser;
import eth.bruises.user.mapper.LogininfoMapper;
import eth.bruises.user.mapper.UserMapper;
import eth.bruises.user.mapper.WxuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 登陆校验的实现类
 *
 * @author bruises
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxuserMapper wxuserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LogininfoMapper logininfoMapper;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 校验是否存在用户，不存在报错
        Logininfo logininfo = logininfoMapper.findByAccount(loginDto);
        if (null == logininfo) {
            throw new GlobalException(GlobalExceptionEnum.INCORRECT_USERNAME_OR_PASSWORD);
        }
        // 判断账号是否禁用
        if (!logininfo.getDisable()) {
            throw new GlobalException(GlobalExceptionEnum.ACCOUNT_DISABLED);
        }
        // 校验密码是否正确
        String verifyPassword = SecureUtil.md5(logininfo.getSalt() + loginDto.getPassword());
        if (!verifyPassword.equals(logininfo.getPassword())) {
            throw new GlobalException(GlobalExceptionEnum.INCORRECT_USERNAME_OR_PASSWORD);
        }
        return loginReturnLoginVo(logininfo);
    }

    /**
     * 抽取登陆方法：1.生成UUID 2.存redis key:uuid value:Logininfo对象 3. 置空敏感信息 4.生成LoginVo
     * @param logininfo
     * @return
     */
    private LoginVo loginReturnLoginVo(Logininfo logininfo) {
        // 生成UUID
        String uuid = UUID.randomUUID().toString();
        // 存redis key:uuid value:Logininfo对象
        redisTemplate.opsForValue().set(uuid, logininfo, 30, TimeUnit.MINUTES);
        // 生成vo，返回前端
        logininfo.setSalt("");
        logininfo.setPassword("");
        return new LoginVo(uuid, logininfo);
    }

    @Override
    public void logout(String token) {
        Object tokenValue = redisTemplate.opsForValue().get(token);
        if (tokenValue != null) {
            redisTemplate.delete(token);
        }
    }

    @Override
    public AjaxResult wxLogin(String code) {
        // 使用code发送请求，获取微信用户的access_token和openid
        String url = String.format(WxConstants.GET_ACCESS_TOKEN_URL, WxConstants.APPID, WxConstants.SECRET, code);
        String jsonStr = HttpUtil.get(url);
        // 包含errcode说明有报错
        if (jsonStr.indexOf("errcode") != -1) {
            throw new GlobalException(GlobalExceptionEnum.ERROR);
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Object accessToken = jsonObject.get("access_token");
        Object openid = jsonObject.get("openid");
        // 拿到openid比对wxUser数据是否存在
        Wxuser wxuser = wxuserMapper.findByOpenid(openid.toString());
        // 判断wxuser是否存在
        if (wxuser != null) {
            // 判断user是否存在,且状态是否正常
            User user = userMapper.selectOne(wxuser.getUserId());
            if (user != null) {
                if (user.getState() == 0) {
                    throw new GlobalException(GlobalExceptionEnum.ACCOUNT_DISABLED);
                }
                // 判断logininfo是否存在
                Logininfo logininfo = logininfoMapper.selectOne(user.getLogininfoId());
                if (logininfo == null) {
                    // 为空则通过user生成logininfo
                    logininfo = new Logininfo();
                    BeanUtil.copyProperties(user, logininfo);
                    logininfo.setType(1);
                    logininfo.setDisable(true);
                    logininfoMapper.add(logininfo);
                    user.setLogininfoId(logininfo.getId());
                    userMapper.update(user);
                }
                // 不为空则直接登陆
                LoginVo loginVo = loginReturnLoginVo(logininfo);
                return AjaxResult.success(loginVo);
            }
        }
        // 以上条件均不满足 则说明未绑定,需要跳转绑定页面，给前端返回goBinding
        // 需要将code和 access_token与openid绑定存入redis，绑定成功后携带code请求后端取出对应token与openid
        WxLoginBo wxLoginBo = new WxLoginBo(accessToken.toString(), openid.toString());
        // 限时30分钟有效
        redisTemplate.opsForValue().set(code, wxLoginBo, 30, TimeUnit.MINUTES);
        // 给前端返回code与需要绑定的信息
        return AjaxResult.error("-10" , "goBinding");
    }
}
