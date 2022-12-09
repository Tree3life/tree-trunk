package life.tree3.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * <p>描述: <br/>
 * 定时清空数据库中的废弃数据(deleted=1) </p>
 * <a>@Author：Jinhui</a>
 * <p>创建时间: 2022/12/8 11:55 </p>
 */
@Configuration
public class DBCleaner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("定时清空数据库");
    }
}
