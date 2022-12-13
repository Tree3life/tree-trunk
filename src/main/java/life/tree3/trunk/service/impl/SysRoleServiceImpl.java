package life.tree3.trunk.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import life.tree3.trunk.dao.SysRolePageMapper;
import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.dto.RoleDto;
import life.tree3.trunk.pojo.entity.SysRole;
import life.tree3.trunk.dao.SysRoleMapper;
import life.tree3.trunk.pojo.entity.SysRolePage;
import life.tree3.trunk.pojo.vo.SysRoleVo;
import life.tree3.trunk.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rupert
 * @since 2022-12-01 23:40:16
 */
@Slf4j
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRolePageMapper rolePageMapper;

    @Override
    public List<SysRole> queryAll() {
        return sysRoleMapper.queryAll();
    }

    @Override
    public List<RoleDto> queryAll(SysRoleVo sysRole) {
        return sysRoleMapper.queryAll(sysRole);
    }

    @Override
    public SysRole queryById(Integer id) {
        return sysRoleMapper.queryById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRole update(SysRoleVo sysRole) {
        Date now = new Date();
        sysRole.setCreateTime(now);
        sysRole.setUpdateTime(now);

        this.sysRoleMapper.update(sysRole);
        //根据角色id逻辑删除该角色对应的所有信息，重新添加所有页面；逻辑删除的信息 ，通过设置的定时任务进行定期清理
        rolePageMapper.logicDelete(sysRole.getId());

        Integer roleId = saveRolePages(sysRole, now);
        return queryById(roleId);
    }


    @Override
    public boolean deleteById(Integer id) {
        return sysRoleMapper.deleteById(id) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRole insert(SysRoleVo sysRole) {
        Date now = new Date();
        sysRole.setCreateTime(now);
        sysRole.setUpdateTime(now);

        this.sysRoleMapper.insert(sysRole);
        saveRolePages(sysRole, new Date());
        return sysRole;
    }

    /**
     * 保存信息
     *
     * @param sysRole
     * @param now
     */
    private Integer saveRolePages(SysRoleVo sysRole, Date now) {
        List<Integer> pages = sysRole.getPages();
        Integer roleId = sysRole.getId();
        if (CollUtil.isNotEmpty(pages) && roleId != null) {


            List<SysRolePage> rolePages = pages.stream()
                    .map(pageId -> new SysRolePage(roleId, pageId, false, now, now))
                    .collect(Collectors.toList());

            rolePageMapper.insertBatch(rolePages);
        }
        return roleId;
    }
}
