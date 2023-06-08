package eth.bruises.test;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import eth.bruises.PetHomeApp;
import eth.bruises.user.domain.Logininfo;
import eth.bruises.user.vo.PayloadVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.HashMap;

@SpringBootTest(classes = PetHomeApp.class)
@RunWith(SpringRunner.class)
public class JWTTest {
    @Test
    public void test() throws Exception{
        // 创建当前时间
        DateTime now = DateTime.now();
        System.out.println(now);

        // 向后偏移30分钟
        DateTime offsetTime = now.offsetNew(DateField.MINUTE, 30);
        System.out.println(offsetTime);

        // 创建有效荷载容器
        HashMap<String, Object> payload = new HashMap<>();
        PayloadVo payloadVo = new PayloadVo();
        Logininfo logininfo = new Logininfo();
        logininfo.setUsername("bruises");
        logininfo.setPhone("18234000121");
        logininfo.setEmail("bruises@gmail.com");
        payloadVo.setLogininfo(logininfo);

        payload.put("payload", payloadVo);

        payload.put(JWT.EXPIRES_AT, offsetTime);
        payload.put(JWT.ISSUED_AT, now);
        payload.put(JWT.NOT_BEFORE, now);

        KeyPair rsa = SecureUtil.generateKeyPair("RSA");
        byte[] encoded = rsa.getPrivate().getEncoded();

        String token = JWTUtil.createToken(payload, encoded);
        System.out.println(token);

        JWT jwt = JWTUtil.parseToken(token);
        JSONObject payloads = jwt.getPayloads();
        String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(payloads);
        System.out.println(jsonString);

        boolean verify = JWTUtil.verify(token, encoded);
        System.out.println(verify);

    }

}
