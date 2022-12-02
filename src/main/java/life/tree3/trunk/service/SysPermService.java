package life.tree3.trunk.service;

import life.tree3.trunk.pojo.entity.SysPerm;

import java.util.List;

/**
 * @author Rupert
 * @since 2022-12-01 23:40:15
 */
public interface SysPermService {

    List<SysPerm> queryAll();

    /**
     * 多条件选择查询：实体作为筛选条件查询数据
     *
     * @param sysPerm 实例对象
     */
    List<SysPerm> queryAll(SysPerm sysPerm);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     */
    SysPerm queryById(Integer id);

    /**
     * 修改数据
     *
     * @param sysPerm 实例对象
     */
    SysPerm update(SysPerm sysPerm);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    boolean deleteById(Integer id);

    /**
     * @param sysPerm 实例对象
     */
    SysPerm insert(SysPerm sysPerm);
}
