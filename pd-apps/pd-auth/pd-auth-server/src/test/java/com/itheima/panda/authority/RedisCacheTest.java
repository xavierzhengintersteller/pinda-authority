package com.itheima.panda.authority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pinda.authority.entity.auth.User;
import org.junit.jupiter.api.Test;

public class RedisCacheTest {
    // 创建ObjectMapper对象用于序列化和反序列化
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void test01() throws JsonProcessingException {
        // 创建user对象  并序列化对象
        User user = User.builder()
                .account("wangwu")
                .name("王五")
                .email("wangwu@example.com")
                .mobile("13700000000")
                .status(true)
                .password("abcdef")
                .build();
        String userJson = MAPPER.writeValueAsString(user);
        System.out.println(userJson);
        // 读数据并反序列化输出
        User jsonUser = MAPPER.readValue(userJson, User.class);
        System.out.println("user ==> " + jsonUser);
    }


}
