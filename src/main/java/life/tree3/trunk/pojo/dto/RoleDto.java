package life.tree3.trunk.pojo.dto;

import life.tree3.trunk.pojo.entity.SysRole;
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
public class RoleDto extends SysRole {
    private static final long serialVersionUID = -6818614634636143932L;

    /**
     * 角色有权限访问的页面
     */
    private List<PageDto> pages;
}
