package life.tree3.trunk.controller;

import life.tree3.trunk.pojo.entity.SysPerm;
import life.tree3.trunk.service.SysPermService;
import lombok.extern.slf4j.Slf4j;
import life.tree3.trunk.settings.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * SysPerm表控制器
 *
 * @author rupert
 * @since 2022-12-01 23:40:15
 */
@Slf4j
@RequestMapping("sysPerm")
@RestController
public class SysPermController {


    @Autowired
    private SysPermService sysPermService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @ResponseResult
    public SysPerm queryById(@PathVariable("id") Integer id) {
        if (log.isTraceEnabled()) {
            log.trace("SysPermController-->queryById(Integer  " + id + ")");
        }

        return sysPermService.queryById(id);
    }

    /**
     * 多条件选择查询
     *
     * @param sysPerm 查询条件：可自主设置为Vo对象
     * @return 响应结果
     */
    @GetMapping("/list")
    @ResponseResult
    public List<SysPerm> list(SysPerm sysPerm) {
        if (log.isTraceEnabled()) {
            log.trace("SysPermController-->list(" + sysPerm.toString() + ")");
        }

        return sysPermService.queryAll(sysPerm);
    }

    @PostMapping("/update")
    @ResponseResult
    public SysPerm update(@RequestBody SysPerm sysPerm) {
        if (log.isTraceEnabled()) {
            log.trace("SysPermController-->updateSysPerm (" + sysPerm.toString() + ")");
        }

        return sysPermService.update(sysPerm);
    }

    @GetMapping("/delete")
    @ResponseResult
    public Boolean deleteById(Integer id) {
        if (log.isTraceEnabled()) {
            log.trace("SysPermController-->deleteById ( Integer  " + id + ") ");
        }

        return sysPermService.deleteById(id);
    }

    @PostMapping("/save")
    @ResponseResult
    public SysPerm save(@RequestBody SysPerm sysPerm) {
        if (log.isTraceEnabled()) {
            log.trace("SysPermController-->saveSysPerm (" + sysPerm.toString() + ")");
        }

        return sysPermService.insert(sysPerm);
    }

}
