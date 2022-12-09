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
 * 角色和权限、资源间也是一对多的关系，一个角色会关联多个权限、资源(SysRolePage)实体
 *
 * @author rupert
 * @since 2022-12-08 09:17:40
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class SysRolePage implements Serializable {

    private static final long serialVersionUID = 758919120489198820L;

    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 页面id
     */
    private Integer pageId;


    private Boolean deleted;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}

