package life.tree3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>描述:  前后端分离开发的服务端
 * 提供以下功能：
 * 1.权限管理
 * 2.web配置:跨域,请求加密&解密,统一封装响应;
 * 3.全局异常处理
 * </p >
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 22:16 </p >
 */
@MapperScan("life.tree3.trunk.dao")
@SpringBootApplication
public class TreeTrunk {
    // TODO: 2022/12/3 0003 解决 `用户管理页面` 多角色报错
    // TODO: 2022/12/3 0003 对身份证及电话号进行加密
    // TODO: 2022/12/3 0003 后期整合SpringSecurity
    public static void main(String[] args) {
        SpringApplication.run(TreeTrunk.class, args);
    }
}
