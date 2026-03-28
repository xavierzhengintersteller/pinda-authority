package com.itheima.panda.authority;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.verify;

public class RedisTest {
    private Jedis jedis;

//    @BeforeEach
//    void setUp() {
//        jedis = new Jedis("127.0.0.1", 6379); // 注意这里用 redis.clients.jedis.Jedis
//    }
    @Test
    void testHash() {
        Jedis mockJedis = Mockito.mock(Jedis.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        map.put("username", "zhangsan");
        map.put("age", "20");
        mockJedis.hmset("user", map);
        Set<String> userKeys = mockJedis.hkeys("user");
        List<String> userValues = mockJedis.hvals("user");
        System.out.println(userKeys);
        System.out.println(userValues);
        // 验证调用
        verify(mockJedis).hkeys("user");
        verify(mockJedis).hvals("user");
    }
//    @AfterEach
//    void tearDown() {
//        if (jedis != null) {
//            jedis.close();
//        }
//    }


}
