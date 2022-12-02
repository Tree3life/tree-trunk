package life.tree3.trunk.service.impl;

import life.tree3.trunk.pojo.entity.SysPerm;
import life.tree3.trunk.dao.SysPermMapper;
import life.tree3.trunk.service.SysPermService;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rupert
 * @since 2022-12-01 23:40:15
 */
@Slf4j
@Service("sysPermService")
public class SysPermServiceImpl implements SysPermService {
    @Resource
    private SysPermMapper sysPermMapper;

    @Override
    public List<SysPerm> queryAll() {
        return sysPermMapper.queryAll();
    }

    @Override
    public List<SysPerm> queryAll(SysPerm sysPerm) {
        return sysPermMapper.queryAll(sysPerm);
    }

    @Override
    public SysPerm queryById(Integer id) {
        return sysPermMapper.queryById(id);
    }

    @Override
    public SysPerm update(SysPerm sysPerm) {
        this.sysPermMapper.update(sysPerm);
        return queryById(sysPerm.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return sysPermMapper.deleteById(id) > 0;
    }

    @Override
    public SysPerm insert(SysPerm sysPerm) {
        this.sysPermMapper.insert(sysPerm);
        return sysPerm;
    }
}
