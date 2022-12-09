package life.tree3.trunk.dao;

import life.tree3.trunk.pojo.dto.UserDto;
import life.tree3.trunk.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUser 表数据库访问层
 *
 * @author Rupert
 * @since 2022-12-01 23:40:16
 */
public interface SysUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);

    /**
     * 查询用户的所有信息
     * 仅按照id进行查询，该用户是否`被锁定/逻辑删除`需进一步通过代码判断
     *
     * @param id
     * @return
     */
    UserDto queryUserInfoById(Integer id);


    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     *
     * @return 对象列表
     * @author rupert
     * @date 2022-12-01 23:40:16
     */
    List<SysUser> queryAll();

    /**
     * 实体作为筛选条件查询数据
     *
     * @param sysUser 实例对象
     * @return 对象列表
     * @author rupert
     * @date 2022-12-01 23:40:16
     */
    List<UserDto> queryAll(SysUser sysUser);
    SysUser querySysUser(SysUser sysUser);

    /**
     * 统计总行数
     *
     * @param sysUser 查询条件
     * @return 总行数
     */
    long count(SysUser sysUser);


    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

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
     * @param sysUser 实例对象
     * @return 影响行数
     */
    boolean insert(SysUser sysUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUser> entities);
}

