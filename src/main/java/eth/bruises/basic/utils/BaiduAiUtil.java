package eth.bruises.basic.utils;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.contentcensor.EImgType;
import eth.bruises.basic.exception.GlobalException;
import eth.bruises.basic.exception.GlobalExceptionEnum;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 百度Ai内容审核工具：文本审核、图片审核
 *
 * @author bruises
 */
public class BaiduAiUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "34085497";
    public static final String API_KEY = "sVKRR6n73utMTSGaCM8OcVuR";
    public static final String SECRET_KEY = "C84UWtDeCpCdI05mSCt7KgjkYhNtj6pk";

    /**
     * 初始化一个AipContentCensor
     */
    public static AipContentCensor client;

    static {
        // 初始化一个AipContentCensor 单例
        client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
        // 设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    /**
     * 图片合规性校验
     *
     * @param filePath
     * @return
     */
    public static Boolean imageCensor(String filePath) {
        // 判断传入的文件路径是否为空，如果为空代表未上传，直接通过图片校验
        if (StringUtils.isEmpty(filePath)) {
            return true;
        }
        // 拼接传入的fastdfs文件服务器与文件路径
        filePath = "http://service-file-primary.java.itsource.cn" + filePath;
        // 调用百度AI审核API
        JSONObject response = client.imageCensorUserDefined(filePath, EImgType.URL, null);
        try {
            // 判断是否审核通过
            if (!response.getString("conclusionType").equals("1")) {
                return false;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 文本合规性校验
     *
     * @param text
     * @return
     */
    public static Boolean textCensor(String text) {
        JSONObject response = client.textCensorUserDefined(text);
        try {
            // 判断是否审核通过
            if (!response.getString("conclusionType").equals("1")) {
                return false;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
