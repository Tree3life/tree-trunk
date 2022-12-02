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
 * 权限模块：页面、功能模块
 * <p>
 * 系统中不提供公共页面，即默认所有的页面都是与角色进行绑定的；
 * 如若有公共页面的需求，可通过配置一个管理公共页面的角色，然后赋予所有用户；（用户与角色是 多对多的关系）(SysPage)表
 *
 * @author rupert
 * @since 2022-12-01 23:40:14
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class SysPage implements Serializable {

    private static final long serialVersionUID = -39353685757860784L;

    private Integer id;

    /**
     * 父级节点{
     * 0：顶级节点;
     * 正数：父节点id；
     * -1：非菜单页}
     */
    private Integer parentId;

    /**
     * 页面、模块名称
     */
    private String title;

    /**
     * 页面功能描述
     */
    private String description;

    /**
     * 页面路径
     */
    private String path;

    /**
     * 页面在项目文件中的路径
     */
    private String filePath;

    /**
     * 权重;用于排序;标识页面的排列次序;
     */
    private Integer weights;

    /**
     * 被删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}

