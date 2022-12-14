package life.tree3.trunk.pojo.entity;

import java.util.Date;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 权限模块：角色资源、角色权限关联表;
 * 角色和权限、资源间也是一对多的关系，一个角色会关联多个权限、资源(SysPagePerm)实体
 *
 * @author rupert
 * @since 2022-12-08 09:17:39
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class SysPagePerm implements Serializable {

    private static final long serialVersionUID = 686847438374028611L;

    private Integer id;

    /**
     * 页面id
     */
    private Integer pageId;

    /**
     * 权限id
     */
    private Integer permId;


    private Boolean deleted;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public SysPagePerm(Integer pageId, Integer permId, Boolean deleted, Date createTime, Date updateTime) {
        this.pageId = pageId;
        this.permId = permId;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

