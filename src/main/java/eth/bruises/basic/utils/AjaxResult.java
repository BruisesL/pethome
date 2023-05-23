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
     * 异步请求结果
     */
    private Boolean success = true;
    /**
     * 请求状态码
     */
    private String code = "0";
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
     * 获取成功状态的AjaxResult，无状态码、无状态信息、无数据实体
     *
     * @return
     */
    public static AjaxResult success() {
        return new AjaxResult();
    }

    /**
     * 获取成功状态的AjaxResult，无数据实体
     *
     * @param code
     * @param message
     * @return
     */
    public static AjaxResult success(String code, String message) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMessage(message);
        return ajaxResult;
    }

    /**
     * 获取成功状态的AjaxResult，仅有数据实体
     *
     * @param data
     * @return
     */
    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 获取成功状态的AjaxResult,所有参数均有
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static AjaxResult success(String code, String message, Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMessage(message);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 获取失败状态的AjaxResult，无数据实体
     *
     * @param code
     * @param message
     * @return
     */
    public static AjaxResult error(String code, String message) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(false);
        ajaxResult.setCode(code);
        ajaxResult.setMessage(message);
        return ajaxResult;
    }

    /**
     * 获取失败状态的AjaxResult，所有参数均有
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static AjaxResult error(String code, String message, Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(false);
        ajaxResult.setCode(code);
        ajaxResult.setMessage(message);
        ajaxResult.setData(data);
        return ajaxResult;
    }
}
