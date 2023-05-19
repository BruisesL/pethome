package eth.bruises.basic.utils;

import lombok.Data;

/**
 * AjaxResult 异步请求返回实体
 *
 * @author bruises
 */
@Data
public class AjaxResult {
    /**
     * 异步请求结果码
     */
    private Boolean success = true;
    /**
     * 请求结果信息
     */
    private String message = "操作成功";
    /**
     * 返回参数
     */
    private Object data = null;

    /**
     * 无参构造
     */
    public AjaxResult() {
    }

    /**
     * 全参构造
     *
     * @param success
     * @param message
     * @param data
     */
    public AjaxResult(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 无返回参数的构造方法
     *
     * @param success
     * @param message
     */
    public AjaxResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * 获取对象
     *
     * @return
     */
    public static AjaxResult me() {
        return new AjaxResult();
    }

    /**
     * 设置success值并且获取
     *
     * @param success
     * @return
     */
    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 设置message值并且获取
     *
     * @param message
     * @return
     */
    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置data值并且获取
     *
     * @param data
     * @return
     */
    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }
}
