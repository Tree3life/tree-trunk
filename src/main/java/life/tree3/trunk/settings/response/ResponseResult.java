package life.tree3.trunk.settings.response;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.*;

/**
 * <p>描述: 标识 该方法的返回结果 需要进行包装处理;<br/>
 * 解析本注解的思路:<br/>
 * 1:拦截请求,判断 该请求是否被本注解进行了标记;<br/>
 * 2:实现接口{@linkplain ResponseBodyAdvice}和{@linkplain ControllerAdvice},在实现类中对被标识方法的返回值进行包装.<br/>
 * 注意:{@linkplain ResponseBody}会使该注解失效.</p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 23:29 </p >
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {

}