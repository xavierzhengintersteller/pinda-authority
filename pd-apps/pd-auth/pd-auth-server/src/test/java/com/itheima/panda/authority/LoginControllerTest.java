import com.itheima.pinda.authority.biz.service.auth.ValidateCodeService;
import com.itheima.pinda.authority.biz.service.auth.impl.AuthManager;
import com.itheima.pinda.authority.controller.auth.LoginController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ValidateCodeService validateCodeService;
    @MockBean
    private AuthManager authManager;
    @Test
    void testCaptcha() throws Exception {
        // 模拟 service 方法（不抛异常就行）
        Mockito.doNothing().when(validateCodeService).create(eq("testKey"), any());

        mockMvc.perform(get("/anno/captcha")
                        .param("key", "testKey"))
                .andExpect(status().isOk());  // 期望 200
    }

}