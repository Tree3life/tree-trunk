package life.tree3.trunk.service;

import life.tree3.trunk.pojo.entity.SysUser;

import java.util.List;

/**
 * @author Rupert
 * @since 2022-12-01 23:40:17
 */
public interface SysUserService {

    List<SysUser> queryAll();

    /**
     * 多条件选择查询：实体作为筛选条件查询数据
     *
     * @param sysUser 实例对象
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     */
    SysUser queryById(Integer id);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    boolean deleteById(Integer id);

    /**
     * @param sysUser 实例对象
     */
    SysUser insert(SysUser sysUser);
}