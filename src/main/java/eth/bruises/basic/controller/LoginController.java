package eth.bruises.basic.controller;


import eth.bruises.basic.dto.LoginDto;
import eth.bruises.basic.service.ILoginService;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户登陆的后端接口
 *
 * @author bruises
 */
@RestController
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@Valid @RequestBody LoginDto loginDto) {
        LoginVo loginVo = loginService.login(loginDto);
        return AjaxResult.success(loginVo);
    }

    @GetMapping("/logout/{token}")
    public AjaxResult logout(@PathVariable("token") String token) {
        loginService.logout(token);
        return AjaxResult.success();
    }

    @GetMapping("/wxLogin/{code}")
    public AjaxResult wxLogin(@PathVariable("code") String code) {
        return loginService.wxLogin(code);
    }

}
