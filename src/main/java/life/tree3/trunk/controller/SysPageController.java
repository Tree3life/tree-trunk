package life.tree3.trunk.controller;

import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.pojo.vo.SysPageVo;
import life.tree3.trunk.service.SysPageService;
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
 * SysPage表控制器
 *
 * @author rupert
 * @since 2022-12-01 23:40:15
 */
@Slf4j
@RequestMapping("sysPage")
@RestController
public class SysPageController {


    @Autowired
    private SysPageService sysPageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @ResponseResult
    public SysPage queryById(@PathVariable("id") Integer id) {
        if (log.isTraceEnabled()) {
            log.trace("SysPageController-->queryById(Integer  " + id + ")");
        }

        return sysPageService.queryById(id);
    }

    /**
     * 多条件选择查询
     *
     * @param sysPage 查询条件：可自主设置为Vo对象
     * @return 响应结果
     */
    @GetMapping("/list")
    @ResponseResult
    public List<PageDto> list(SysPageVo sysPage) {
        if (log.isTraceEnabled()) {
            log.trace("SysPageController-->list(" + sysPage.toString() + ")");
        }

        return sysPageService.queryAll(sysPage);
    }

    @PostMapping("/update")
    @ResponseResult
    public SysPage update(@RequestBody SysPageVo sysPage) {
        if (log.isTraceEnabled()) {
            log.trace("SysPageController-->updateSysPage (" + sysPage.toString() + ")");
        }

        return sysPageService.update(sysPage);
    }

    @PostMapping("/delete")
    @ResponseResult
    public Boolean deleteById(@RequestBody SysPage page) {
        Objects.requireNonNull(page);
        if (log.isTraceEnabled()) {
            log.trace("SysPageController-->deleteById ( Integer  " + page.getId() + ") ");
        }

        return sysPageService.deleteById(page.getId());
    }

    @PostMapping("/save")
    @ResponseResult
    public SysPage save(@RequestBody SysPageVo sysPage) {
        if (log.isTraceEnabled()) {
            log.trace("SysPageController-->saveSysPage (" + sysPage.toString() + ")");
        }

        return sysPageService.insert(sysPage);
    }

}
