package eth.bruises.basic.controller;

import eth.bruises.basic.dto.EmailCodeDto;
import eth.bruises.basic.service.IVerifyCodeService;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.dto.PhoneCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 获取验证码的controller接口
 *
 * @author bruises
 */
@RestController
@RequestMapping("/verify")
@Validated
public class VerifyCodeController {
    @Autowired
    private IVerifyCodeService verifyCodeService;

    @GetMapping("/imageCode/{uuid}")
    public String imageCode(@NotBlank(message = "参数异常！") @PathVariable("uuid") String uuid) {
        return verifyCodeService.imageCode(uuid);
    }

    @PostMapping("/phoneCode")
    public AjaxResult phoneCode(@Valid  @RequestBody PhoneCodeDto dto) {
        return verifyCodeService.phoneCode(dto);
    }

    @PostMapping("/emailCode")
    public AjaxResult emailCode(@Valid  @RequestBody EmailCodeDto dto) {
        return verifyCodeService.emailCode(dto);
    }


}
