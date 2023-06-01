package eth.bruises.basic.service;

import eth.bruises.basic.dto.EmailCodeDto;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.dto.PhoneCodeDto;

/**
 * 验证码接口的service层
 *
 * @author bruises
 */
public interface IVerifyCodeService {

    /**
     * 生成图形验证码的base64值
     *
     * @param uuid
     * @return
     */
    String imageCode(String uuid);

    /**
     * 发送手机验证码
     *
     * @param dto
     * @return AjaxResult
     */
    AjaxResult phoneCode(PhoneCodeDto dto);
    /**
     * 发送邮件验证码
     *
     * @param dto
     * @return AjaxResult
     */
    AjaxResult emailCode(EmailCodeDto dto);
}
