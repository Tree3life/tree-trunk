package life.tree3.trunk.pojo.dto;

import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.pojo.entity.SysPerm;
import lombok.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/2 0002 9:44 </p>
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PageDto extends SysPage {
    private static final long serialVersionUID = -759448515934151734L;

    /**
     * 访问本页面所必需的权限
     */
    private List<SysPerm> perms;
}
