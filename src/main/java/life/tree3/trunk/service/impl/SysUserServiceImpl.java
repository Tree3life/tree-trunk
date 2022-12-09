package life.tree3.trunk.service.impl;

import life.tree3.trunk.dao.SysUserRoleMapper;
import life.tree3.trunk.pojo.dto.UserDto;
import life.tree3.trunk.pojo.entity.SysUser;
import life.tree3.trunk.dao.SysUserMapper;
import life.tree3.trunk.pojo.entity.SysUserRole;
import life.tree3.trunk.pojo.vo.SysUserVo;
import life.tree3.trunk.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rupert
 * @since 2022-12-01 23:40:17
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

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


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDto update(SysUserVo sysUser) {
        this.sysUserMapper.update(sysUser);

        Integer currentUserId = sysUser.getId();
        //Step 1: 根据userId删除记录
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(currentUserId);
        userRoleMapper.deleteByEntity(sysUserRole);

        //Step 2: 添加所有用户
        List<Integer> roles = sysUser.getRoles();
        List<SysUserRole> userRoles = roles.stream().map(item -> {
            Date now = new Date();
            return new SysUserRole(currentUserId, item, false, now, now);
        }).collect(Collectors.toList());
        userRoleMapper.insertBatch(userRoles);

        return sysUserMapper.queryUserInfoById(currentUserId);
    }

    @Override
    public boolean deleteById(Integer id) {
        return sysUserMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser insert(SysUserVo sysUser) {
        this.sysUserMapper.insert(sysUser);

        List<Integer> roles = sysUser.getRoles();
        if (roles != null && roles.size() > 0) {
            Integer userId = sysUser.getId();
            Date now = new Date();

            List<SysUserRole> sysUserRoles = roles.stream().map(roleId -> new SysUserRole(userId, roleId, false, now, now)).collect(Collectors.toList());
            userRoleMapper.insertBatch(sysUserRoles);
        }
        return sysUser;
    }
}
