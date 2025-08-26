package com.itheima.pinda.authority.controller.auth;

import com.itheima.pinda.authority.dto.auth.LoginDTO;
import com.itheima.pinda.authority.dto.auth.LoginParamDTO;
import com.itheima.pinda.authority.service.auth.ValidateCodeService;
import com.itheima.pinda.authority.service.auth.impl.AuthManager;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.exception.BizException;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/anno")
@Api(value = "UserAuthController", tags = "登录")
@Slf4j
public class LoginController  extends BaseController {
    @Autowired
    private ValidateCodeService validateCodeService;
    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/captcha", produces = "image/png")
    @SysLog("")
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        this.validateCodeService.create(key, response);
    }
    @Autowired
    private AuthManager authManager;//认证管理器对象

    /**
     * 登录认证
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    @SysLog("")
    public R<LoginDTO> login(@Validated @RequestBody LoginParamDTO login)
            throws BizException {
        log.info("account={}", login.getAccount());
        if (this.validateCodeService.check(login.getKey(), login.getCode())) {
            return this.authManager.login(login.getAccount(), login.getPassword());
        }
        return this.success(null);
    }
}
