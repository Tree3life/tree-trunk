package life.tree3.trunk.dao;

import life.tree3.trunk.pojo.entity.SysRolePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysRolePage 表数据库访问层
 *
 * @author rupert
 * @since 2022-12-08 09:17:40
 */
public interface SysRolePageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRolePage queryById(Integer id);


    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     *
     * @return 对象列表
     * @author rupert
     * @date 2022-12-08 09:17:40
     */
    List<SysRolePage> queryAll();

    /**
     * 实体作为筛选条件查询数据
     *
     * @param sysRolePage 实例对象
     * @return 对象列表
     * @author rupert
     * @date 2022-12-08 09:17:40
     */
    List<SysRolePage> queryAll(SysRolePage sysRolePage);

    /**
     * 统计总行数
     *
     * @param sysRolePage 查询条件
     * @return 总行数
     */
    long count(SysRolePage sysRolePage);


    /**
     * 修改数据
     *
     * @param sysRolePage 实例对象
     * @return 影响行数
     */
    int update(SysRolePage sysRolePage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 新增数据
     *
     * @param sysRolePage 实例对象
     * @return 影响行数
     */
    int insert(SysRolePage sysRolePage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRolePage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRolePage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRolePage> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRolePage> entities);
}

