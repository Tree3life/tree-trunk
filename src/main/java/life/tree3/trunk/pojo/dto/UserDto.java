package life.tree3.trunk.pojo.dto;

import cn.hutool.db.Page;
import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.pojo.entity.SysPerm;
import life.tree3.trunk.pojo.entity.SysRole;
import life.tree3.trunk.pojo.entity.SysUser;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 页面
     * 本属性理论上是可忽略的，交由前端人员根据roles属性进行维护
     */
    private List<PageDto> pages;

    /**
     * 权限
     * 同 pages
     */
    private List<SysPerm> perms;

//    UserDto


    //todo 去重
    public List<PageDto> getPages() {
        if (null == roles || roles.size() == 0) {
            return pages;
        }

        pages = new ArrayList<PageDto>(16);

        this.roles.forEach(roleDto -> {
            List<PageDto> rolePages = roleDto.getPages();
            if (null != rolePages && rolePages.size() > 0) {
                pages.addAll(rolePages);
            }
        });
        if (pages.size() > 0) {
            pages = pages.stream().distinct().collect(Collectors.toList());
        }
        //pages = new ArrayList<>(new LinkedHashSet<>(pages));//去重

        return pages;
    }

    //todo 去重
    public List<SysPerm> getPerms() {
        if (null == pages || pages.size() == 0) {
            return perms;
        }

        perms = new ArrayList<SysPerm>();
        //处理pages中包含的用户能够访问的所有权限信息
        pages.forEach(page -> {
            List<SysPerm> pagePerms = page.getPerms();
            if (pagePerms != null && pagePerms.size() > 0) {
                perms.addAll(pagePerms);
            }
        });

        if (perms.size() > 0) {
            perms = perms.stream().distinct().collect(Collectors.toList());
        }
        return perms;
    }
}
