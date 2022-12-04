package life.tree3.task;

import life.tree3.trunk.dao.SysPageMapper;
import life.tree3.trunk.pojo.dto.PageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/3 0003 17:17 </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAAAA {
    @Resource
    private SysPageMapper pageMapper;

    @Test
    public void aa() {
        List<PageDto> pageDtos = pageMapper.queryPagesForRole(21);
        System.out.println(pageDtos);
    }
}
