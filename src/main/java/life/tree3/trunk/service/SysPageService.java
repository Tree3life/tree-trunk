package life.tree3.trunk.service;

import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.pojo.vo.SysPageVo;

import java.util.List;

/**
 * @author Rupert
 * @since 2022-12-01 23:40:15
 */
public interface SysPageService {

    List<SysPage> queryAll();

    /**
     * 多条件选择查询：实体作为筛选条件查询数据
     *
     * @param sysPage 实例对象
     */
    List<PageDto> queryAll(SysPageVo sysPage);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     */
    SysPage queryById(Integer id);

    /**
     * 修改数据
     *
     * @param sysPage 实例对象
     */
    SysPage update(SysPageVo sysPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    boolean deleteById(Integer id);

    /**
     * @param sysPage 实例对象
     */
    SysPage insert(SysPageVo sysPage);
}
