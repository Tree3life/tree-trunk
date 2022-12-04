package life.tree3.trunk.controller;

import life.tree3.trunk.pojo.dto.UserDto;
import life.tree3.trunk.pojo.entity.SysUser;
import life.tree3.trunk.pojo.vo.SysUserVo;
import life.tree3.trunk.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import life.tree3.trunk.settings.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

/**
 * SysUser表控制器
 *
 * @author rupert
 * @since 2022-12-01 23:40:17
 */
@Slf4j
@RequestMapping("sysUser")
@RestController
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @ResponseResult
    public SysUser queryById(@PathVariable("id") Integer id) {
        if (log.isTraceEnabled()) {
            log.trace("SysUserController-->queryById(Integer  " + id + ")");
        }

        return sysUserService.queryById(id);
    }

    /**
     * 多条件选择查询
     *
     * @param sysUser 查询条件：可自主设置为Vo对象
     * @return 响应结果
     */
    @GetMapping("/list")
    @ResponseResult
    public List<UserDto> list(SysUser sysUser) {
        if (log.isTraceEnabled()) {
            log.trace("SysUserController-->list(" + sysUser.toString() + ")");
        }

        return sysUserService.queryAll(sysUser);
    }

    @PostMapping("/update")
    @ResponseResult
    public SysUser update(@RequestBody SysUserVo sysUser) {
        if (log.isTraceEnabled()) {
            log.trace("SysUserController-->updateSysUser (" + sysUser.toString() + ")");
        }
        System.out.println("======update======");
        System.out.println(sysUser);
        System.out.println("======update======");
        return sysUserService.update(sysUser);
    }

    @PostMapping("/delete")
    @ResponseResult
    public Boolean deleteById(@RequestBody SysUser user) {
        Objects.requireNonNull(user);
        if (log.isTraceEnabled()) {
            log.trace("SysUserController-->deleteById ( Integer  " + user.getId() + ") ");
        }

        return sysUserService.deleteById(user.getId());
    }

    @PostMapping("/save")
    @ResponseResult
    public SysUser save(@RequestBody SysUserVo sysUser) {
        if (log.isTraceEnabled()) {
            log.trace("SysUserController-->saveSysUser (" + sysUser.toString() + ")");
        }
        System.out.println("======save======");
        System.out.println(sysUser);
        System.out.println("======save======");

        return sysUserService.insert(sysUser);
    }

}
