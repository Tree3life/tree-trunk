package life.tree3.trunk.pojo.vo;

import life.tree3.trunk.pojo.entity.SysPage;
import lombok.*;

import java.util.List;

/**
 * <p>描述: <br/>
 * </p>
 * <a>@Author：Jinhui</a>
 * <p>创建时间: 2022/12/10 16:14 </p>
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysPageVo extends SysPage {
    /**
     * 角色
     */
    private List<Integer> perms;
    private int size=20;
    private int current=1;
}
