package life.tree3.trunk.service.impl;

import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.dao.SysPageMapper;
import life.tree3.trunk.service.SysPageService;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rupert
 * @since 2022-12-01 23:40:15
 */
@Slf4j
@Service("sysPageService")
public class SysPageServiceImpl implements SysPageService {
    @Resource
    private SysPageMapper sysPageMapper;

    @Override
    public List<SysPage> queryAll() {
        return sysPageMapper.queryAll();
    }

    @Override
    public List<SysPage> queryAll(SysPage sysPage) {
        return sysPageMapper.queryAll(sysPage);
    }

    @Override
    public SysPage queryById(Integer id) {
        return sysPageMapper.queryById(id);
    }

    @Override
    public SysPage update(SysPage sysPage) {
        this.sysPageMapper.update(sysPage);
        return queryById(sysPage.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return sysPageMapper.deleteById(id) > 0;
    }

    @Override
    public SysPage insert(SysPage sysPage) {
        this.sysPageMapper.insert(sysPage);
        return sysPage;
    }
}
