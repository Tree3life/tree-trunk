package life.tree3.trunk.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>描述:
 * 项目的自定义配置
 * </p >
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 22:37 </p >
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "tree3")
public class Tree3Properties {
    /**
     * JWT 密钥
     */
    private String jwtSecret;

    /**
     * JWT Token有效时间
     */
    private long effectiveTime;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 密码加/解密md5散列次数
     */
    private Integer hashIterations;

    /**
     * 开启全局异常处理捕获异常后打印堆栈信息
     */
    private boolean showCase;
}
