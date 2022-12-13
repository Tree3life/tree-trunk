package life.tree3.trunk.dao;

import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.pojo.vo.SysPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysPage 表数据库访问层
 *
 * @author Rupert
 * @since 2022-12-01 23:40:14
 */
public interface SysPageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPage queryById(Integer id);


    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     *
     * @return 对象列表
     * @author rupert
     * @date 2022-12-01 23:40:14
     */
    List<SysPage> queryAll();

    /**
     * 角色允许访问的页面的信息
     *
     * @param roleId
     * @return
     */
    List<PageDto> queryPagesForRole(int roleId);

    /**
     * 实体作为筛选条件查询数据
     *
     * @param sysPage 实例对象
     * @return 对象列表
     * @author rupert
     * @date 2022-12-01 23:40:14
     */
    List<PageDto> queryAll(SysPageVo sysPage);

    /**
     * 统计总行数
     *
     * @param sysPage 查询条件
     * @return 总行数
     */
    long count(SysPage sysPage);


    /**
     * 修改数据
     *
     * @param sysPage 实例对象
     * @return 影响行数
     */
    int update(SysPage sysPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键逻辑删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int logicDeleteById(Integer id);

    /**
     * 新增数据
     *
     * @param sysPage 实例对象
     * @return 影响行数
     */
    int insert(SysPage sysPage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysPage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPage> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysPage> entities);
}

