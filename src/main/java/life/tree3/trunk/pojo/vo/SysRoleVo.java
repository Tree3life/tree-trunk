package life.tree3.trunk.pojo.vo;

import life.tree3.trunk.pojo.entity.SysRole;
import lombok.*;

import java.util.List;

/**
 * <p>描述: <br/>
 * </p>
 * <a>@Author：Jinhui</a>
 * <p>创建时间: 2022/12/12 13:06 </p>
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleVo extends SysRole {
    /**
     * 角色
     */
    private List<Integer> pages;
    private int size=20;
    private int current=1;
}
