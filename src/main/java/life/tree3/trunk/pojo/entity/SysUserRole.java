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
 * 权限模块：用户角色关联表
 * 描述指定用户与角色间的依赖关系。其中用户表与角色表是一对多的关系，一个用户可以拥有多个角色(SysUserRole)实体
 *
 * @author rupert
 * @since 2022-12-08 09:17:41
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 155636149625063586L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;


    private Boolean deleted;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public SysUserRole(Integer userId, Integer roleId, Boolean deleted, Date createTime, Date updateTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

