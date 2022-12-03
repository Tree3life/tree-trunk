package life.tree3.trunk.pojo.dto;

import life.tree3.trunk.pojo.entity.SysUser;
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
public class UserDto extends SysUser {
    private static final long serialVersionUID = -7611492201597788003L;

    /**
     * 用户拥有的角色
     */
    private List<RoleDto> roles;
}
