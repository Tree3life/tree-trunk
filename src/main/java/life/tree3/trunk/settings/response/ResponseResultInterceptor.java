package life.tree3.trunk.settings.response;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>描述: 自定义的请求拦截器：检查请求方法上是否被@ResponseResult标识<br/>
 * 拦截请求并检查该请求对应的方法上是否带有@{@linkplain ResponseResult}标识<br/>
 * 有--->在请求中添加 标记【RESPONSE_RESULT_ANN, clazz.getAnnotation({@linkplain ResponseResult}.class)】;<br/>
 * 无--->不做处理；</p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 23:29 </p >
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    /**
     * 标记：某个响应的返回值 需要进行封装处理
     */
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    /**
     * 拦截请求，判断该请求对应的方法是否被 {@link ResponseResult}标识;<br/>
     * 请求方法被标识-->在请求中添加 {@value RESPONSE_RESULT_ANN}标记；<br/>
     * 请求方法未被标识-->不做任何处理；
     * <p>
     * 处理请求的逻辑：<Br/>
     * 1.若被{@linkplain ResponseBody}标注，直接放行，不做处理 ;<Br/>
     * 2.被{@linkplain ResponseResult}标识方法的返回值 ,需要在 {@linkplain ResponseResultHandler }中 重新封装;
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断该方法是否被`@RequestMapping`标注【通过判断该方法是否是HandlerMethod的一个实例达成目的】
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Class<?> clazz = handlerMethod.getBeanType();
        final Method method = handlerMethod.getMethod();

        if (clazz.isAnnotationPresent(ResponseBody.class) || method.isAnnotationPresent(ResponseBody.class)) {
            return true;
        }

        if (clazz.isAnnotationPresent(ResponseResult.class)) {//类对象上 是否被@ResponseResult标注
            log.debug("标记请求---class，添加打包标记：" + clazz.getSimpleName() + ":::" + clazz.getAnnotation(ResponseResult.class));
            request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
        } else if (method.isAnnotationPresent(ResponseResult.class)) {//方法是否被 @ResponseResult标注
            log.debug("标记请求---method，添加打包标记：" + method.getName() + ":::" + method.getAnnotation(ResponseResult.class));
            request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
        }
        return true;
    }
}
