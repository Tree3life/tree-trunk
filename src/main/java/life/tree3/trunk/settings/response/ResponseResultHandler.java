package life.tree3.trunk.settings.response;

import life.tree3.trunk.settings.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>描述: 统一处理返回结果的切面（Aop）;<br/>
 * 处理响应报文，对被{@linkplain ResponseResult}标注，被{@linkplain ResponseResultInterceptor}添加标记的方法 返回值进行 包装；</p>
 * </p >
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 23:29 </p >
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";
    private Logger logger = LoggerFactory.getLogger(ResponseResultHandler.class);

    /**
     * Desc: 对beforeBodyWrite()的执行者进行筛选;<br/>
     *
     * @return true: 执行beforeBodyWrite()；<br/>
     * false: 不执行beforeBodyWrite().
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        //判断请求是否有 包装 标记
        ResponseResult responseHandler = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);

        return responseHandler != null;
    }

    /**
     * Desc：将 返回结果 包装 为{@linkplain RespResult};<br/>
     *
     * @return 加工后的响应结果
     * @apiNote 返回结果为以下几种类型时不在此处做任何处理，直接返回：<br/>
     * {@linkplain String},{@linkplain ModelAndView},{@linkplain RespResult}
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        logger.debug("封装返回结果");

        //处理手动封装的响应结果
        if (body instanceof RespResult) {
            return body;
        }

        /**
         * SpringMVC中后期可能会对String类型的返回值做特殊处理，因此不对String类型的返回值做任何处理
         * 如果有向前台返回一个String类型的需求：可以通过直接该控制器的返回值类型设置为RespResult，直接调用RespResult.success(body)
         */
        if (body instanceof String) {
            return body;
        }
        if (body instanceof ModelAndView) {
            throw new BizException(ResponseCode.MEANINGLESS_USE, "在返回值类型为ModelAndView的方法上使用@ResponseResult是无意义的！");
        }

        //正常数据封装
        return RespResult.success(body);
    }
}
