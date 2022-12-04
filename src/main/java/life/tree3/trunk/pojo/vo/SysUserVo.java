package life.tree3.trunk.pojo.vo;

import life.tree3.trunk.pojo.entity.SysUser;
import lombok.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/3 0003 23:49 </p>
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserVo extends SysUser {
    /**
     * 角色
     */
    private List<Integer> roles;
    private int size=20;
    private int current=1;
}
