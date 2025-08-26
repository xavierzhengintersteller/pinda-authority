import com.itheima.pinda.AuthorityApplication;
import com.itheima.pinda.authority.dao.auth.ResourceMapper;
import com.itheima.pinda.authority.dto.auth.ResourceQueryDTO;
import com.itheima.pinda.authority.entity.auth.Resource;
import com.itheima.pinda.authority.service.auth.ResourceService;
import com.itheima.pinda.common.constant.CacheKey;
import net.oschina.j2cache.CacheChannel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AuthorityApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ResourceServiceImplTest {

    @Autowired
    private ResourceService resourceService;

    @MockBean
    private ResourceMapper resourceMapper;

    @MockBean
    private CacheChannel cache;

    @Test
    void testFindVisibleResource_shouldCacheAndLog() {
        ResourceQueryDTO queryDTO = new ResourceQueryDTO();
        queryDTO.setUserId(1001L);

        Resource r1 = new Resource();
        r1.setMethod("GET");
        r1.setUrl("/user/page");

        Resource r2 = new Resource();
        r2.setMethod("POST");
        r2.setUrl("/user/save");

        List<Resource> mockResources = Arrays.asList(r1, r2);
        when(resourceMapper.findVisibleResource(queryDTO)).thenReturn(mockResources);

        List<Resource> result = resourceService.findVisibleResource(queryDTO);

        assertNotNull(result);
        assertEquals(2, result.size());

        List<String> expectedUserResource = Arrays.asList("GET/user/page", "POST/user/save");
        verify(cache, times(1)).set(
                eq(CacheKey.USER_RESOURCE),
                eq("1001"),
                eq(expectedUserResource)
        );

        // 这里没有直接断言日志，而是依赖 @Slf4j 的输出
        // 你运行测试时能看到类似日志：
        // User [1001] 可访问资源写入缓存: [GET/user/page, POST/user/save]
    }
}
