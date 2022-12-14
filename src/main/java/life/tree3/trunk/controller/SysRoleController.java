package life.tree3.trunk.controller;

import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.dto.RoleDto;
import life.tree3.trunk.pojo.entity.SysRole;
import life.tree3.trunk.pojo.vo.SysRoleVo;
import life.tree3.trunk.service.SysRoleService;
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
 * SysRole表控制器
 *
 * @author rupert
 * @since 2022-12-01 23:40:16
 */
@Slf4j
@RequestMapping("sysRole")
@RestController
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @ResponseResult
    public SysRole queryById(@PathVariable("id") Integer id) {
        if (log.isTraceEnabled()) {
            log.trace("SysRoleController-->queryById(Integer  " + id + ")");
        }

        return sysRoleService.queryById(id);
    }

    /**
     * 多条件选择查询
     *
     * @param sysRole 查询条件：可自主设置为Vo对象
     * @return 响应结果
     */
    @GetMapping("/list")
    @ResponseResult
    public List<RoleDto> list(SysRoleVo sysRole) {
        if (log.isTraceEnabled()) {
            log.trace("SysRoleController-->list(" + sysRole.toString() + ")");
        }
        return sysRoleService.queryAll(sysRole);
    }

    @PostMapping("/update")
    @ResponseResult
    public SysRole update(@RequestBody SysRoleVo sysRole) {
        if (log.isTraceEnabled()) {
            log.trace("SysRoleController-->updateSysRole (" + sysRole.toString() + ")");
        }

        return sysRoleService.update(sysRole);
    }

    @PostMapping("/delete")
    @ResponseResult
    public Boolean deleteById(@RequestBody SysRole sysRole) {
        Objects.requireNonNull(sysRole);
        if (log.isTraceEnabled()) {
            log.trace("SysRoleController-->deleteById ( Integer  " + sysRole.getId() + ") ");
        }

        return sysRoleService.deleteById(sysRole.getId());
    }

    @PostMapping("/save")
    @ResponseResult
    public SysRole save(@RequestBody SysRoleVo sysRole) {
        if (log.isTraceEnabled()) {
            log.trace("SysRoleController-->saveSysRole (" + sysRole.toString() + ")");
        }

        return sysRoleService.insert(sysRole);
    }

}
