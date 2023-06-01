package eth.bruises.basic.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 短信发送工具类
 *
 * @author bruises
 */
public class SendSmsUtil {

    //用户名
    public static final String UID = "bruises";
    //秘钥
    public static final String KEY = "FC5982B1BF4475D4C22A1C5526929E44";

    /**
     * 发送短信
     *
     * @param phone   手机号
     * @param content 发送内容
     * @return 返回值
     */
    public static String sendSms(String phone, String content) {
        PostMethod post = null;
        try {
            HttpClient client = new HttpClient();
            post = new PostMethod("http://utf8.api.smschinese.cn");
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
            NameValuePair[] data = {new NameValuePair("Uid", SendSmsUtil.UID),
                    new NameValuePair("Key", SendSmsUtil.KEY),
                    new NameValuePair("smsMob", phone),
                    new NameValuePair("smsText", content)};
            post.setRequestBody(data);

            client.executeMethod(post);
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:" + statusCode);
            String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return null;
    }
}