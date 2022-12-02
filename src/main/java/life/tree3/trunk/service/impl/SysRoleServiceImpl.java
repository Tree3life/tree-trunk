package life.tree3.trunk.service.impl;

import life.tree3.trunk.pojo.entity.SysRole;
import life.tree3.trunk.dao.SysRoleMapper;
import life.tree3.trunk.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rupert
 * @since 2022-12-01 23:40:16
 */
@Slf4j
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> queryAll() {
        return sysRoleMapper.queryAll();
    }

    @Override
    public List<SysRole> queryAll(SysRole sysRole) {
        return sysRoleMapper.queryAll(sysRole);
    }

    @Override
    public SysRole queryById(Integer id) {
        return sysRoleMapper.queryById(id);
    }

    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleMapper.update(sysRole);
        return queryById(sysRole.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return sysRoleMapper.deleteById(id) > 0;
    }

    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleMapper.insert(sysRole);
        return sysRole;
    }
}
