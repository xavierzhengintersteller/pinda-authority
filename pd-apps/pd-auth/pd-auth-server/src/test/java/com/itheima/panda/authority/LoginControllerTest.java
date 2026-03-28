package com.itheima.panda.authority;

import com.itheima.pinda.authority.biz.service.auth.ValidateCodeService;
import com.itheima.pinda.authority.biz.service.auth.impl.AuthManager;
import com.itheima.pinda.authority.controller.auth.LoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ValidateCodeService validateCodeService;
    @Mock
    private AuthManager authManager;
    @InjectMocks
    private LoginController loginController;
    @Test
    void testCaptcha() throws Exception {
        Mockito.doNothing().when(validateCodeService).create(eq("testKey"), any(HttpServletResponse.class));

        // 直接调用方法，而不是 mockMvc.perform
        loginController.captcha("testKey", Mockito.mock(HttpServletResponse.class));
    }

}