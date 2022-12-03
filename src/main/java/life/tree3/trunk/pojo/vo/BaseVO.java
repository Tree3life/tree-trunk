package life.tree3.trunk.pojo.vo;

import lombok.*;

import java.util.Date;

/**
 * <p>描述:
 * Vo对象与页面中的表单对象对应<br/>
 * 可视为系统的入参
 * </p >
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/2 0002 9:37 </p >
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class BaseVO {
    private int size=20;
    private int current=1;
    private Date startTime;
    private Date endTime;
}
