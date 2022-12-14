package life.tree3.trunk.service;

import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.dto.RoleDto;
import life.tree3.trunk.pojo.entity.SysRole;
import life.tree3.trunk.pojo.vo.SysRoleVo;

import java.util.List;

/**
 * @author Rupert
 * @since 2022-12-01 23:40:16
 */
public interface SysRoleService {

    List<SysRole> queryAll();

    /**
     * 多条件选择查询：实体作为筛选条件查询数据
     *
     * @param sysRole 实例对象
     */
    List<RoleDto> queryAll(SysRoleVo sysRole);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     */
    SysRole queryById(Integer id);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     */
    SysRole update(SysRoleVo sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    boolean deleteById(Integer id);

    /**
     * @param sysRole 实例对象
     */
    SysRole insert(SysRoleVo sysRole);
}
