package life.tree3.trunk.service.impl;

import cn.hutool.core.collection.CollUtil;
import life.tree3.trunk.dao.SysPagePermMapper;
import life.tree3.trunk.pojo.dto.PageDto;
import life.tree3.trunk.pojo.entity.SysPage;
import life.tree3.trunk.dao.SysPageMapper;
import life.tree3.trunk.pojo.entity.SysPagePerm;
import life.tree3.trunk.pojo.vo.SysPageVo;
import life.tree3.trunk.service.SysPageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rupert
 * @since 2022-12-01 23:40:15
 */
@Slf4j
@Service("sysPageService")
public class SysPageServiceImpl implements SysPageService {
    @Resource
    private SysPageMapper sysPageMapper;

    @Resource
    private SysPagePermMapper pagePermMapper;

    @Override
    public List<SysPage> queryAll() {
        return sysPageMapper.queryAll();
    }

    @Override
    public List<PageDto> queryAll(SysPageVo sysPage) {
        return sysPageMapper.queryAll(sysPage);
    }

    @Override
    public SysPage queryById(Integer id) {
        return sysPageMapper.queryById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysPage update(SysPageVo sysPage) {
        Date now = new Date();
        sysPage.setUpdateTime(now);

        this.sysPageMapper.update(sysPage);
        pagePermMapper.logicDelete(sysPage.getId());

        insertBatchPagePerms(sysPage, now);
        return queryById(sysPage.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return sysPageMapper.deleteById(id) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysPage insert(SysPageVo sysPage) {
        Date now = new Date();
        sysPage.setCreateTime(now);
        sysPage.setUpdateTime(now);

        this.sysPageMapper.insert(sysPage);
        insertBatchPagePerms(sysPage, now);
        //所需权限
        return sysPage;
    }

    private void insertBatchPagePerms(SysPageVo sysPage, Date now) {
        List<Integer> perms = sysPage.getPerms();
        if (CollUtil.isNotEmpty(perms)) {
            List<SysPagePerm> pagePerms = perms.stream().map(pId -> new SysPagePerm(sysPage.getId(), pId, false, now, now))
                    .collect(Collectors.toList());
            pagePermMapper.insertBatch(pagePerms);
        }
    }
}
