package life.tree3.trunk.service.impl;

import life.tree3.trunk.pojo.dto.UserDto;
import life.tree3.trunk.pojo.entity.SysUser;
import life.tree3.trunk.dao.SysUserMapper;
import life.tree3.trunk.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rupert
 * @since 2022-12-01 23:40:17
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> queryAll() {
        return sysUserMapper.queryAll();
    }

    @Override
    public List<UserDto> queryAll(SysUser sysUser) {
        return sysUserMapper.queryAll(sysUser);
    }

    @Override
    public SysUser querySysUser(SysUser user) {
        return sysUserMapper.querySysUser(user);
    }

    @Override
    public UserDto queryUserInfo(Integer id) {
        return sysUserMapper.queryUserInfoById(id);
    }

    @Override
    public SysUser queryById(Integer id) {
        return sysUserMapper.queryById(id);
    }


    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserMapper.update(sysUser);
        return queryById(sysUser.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return sysUserMapper.deleteById(id) > 0;
    }

    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserMapper.insert(sysUser);
        return sysUser;
    }
}
