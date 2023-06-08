package eth.bruises.basic.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.vo.PayloadVo;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt鉴权验证Token相关工具类
 * @author bruises
 */
public class JwtUtil {
    /**
     * 定义私钥的码
     */
    private static byte[] encodes = "awlkjdnawlkjdnq12121312".getBytes();


    // 生成私钥
//    static {
//        KeyPair rsa = SecureUtil.generateKeyPair("RSA");
//        encodes = rsa.getPrivate().getEncoded();
//    }

    /**
     * 创建token，有效荷载存储用户的登陆信息与权限/菜单信息
     * @param payloadVo
     * @return
     */
    public static String createToken(PayloadVo payloadVo) {
        // 创建有效荷载容器
        Map<String, Object> payloadMap = new HashMap<>();
        // 创建当前时间
        DateTime now = DateTime.now();

        // 向后偏移30分钟
        DateTime offsetTime = now.offsetNew(DateField.MINUTE, 30);
        payloadMap.put(JWT.EXPIRES_AT, offsetTime);
        payloadMap.put(JWT.ISSUED_AT, now);
        payloadMap.put(JWT.NOT_BEFORE, now);

        // 3.设置自己的载荷数据
        payloadMap.put("payload", payloadVo);
        return JWTUtil.createToken(payloadMap, encodes);
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static PayloadVo parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        JSONObject payloads = jwt.getPayloads();
        if (payloads == null){
            throw new GlobalException(GlobalExceptionEnum.ERROR);
        }
        Object payload = payloads.get("payload");
        if (payload == null){
            return null;
        }
        return com.alibaba.fastjson.JSONObject.parseObject(com.alibaba.fastjson.JSONObject.toJSONString(payload), PayloadVo.class);
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        // 如果校验为正确通过的token，则继续判断是否过期
        if (JWTUtil.verify(token, encodes)) {
//            try {
//                JWTValidator.of(token).validateAlgorithm();
                return true;
//            } catch (ValidateException e) {
//                e.printStackTrace();
//            }
        }
        return false;
    }
}
