package life.tree3.trunk.settings.exception;

import life.tree3.trunk.config.Tree3Properties;
import life.tree3.trunk.settings.response.RespResult;
import life.tree3.trunk.settings.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>描述:
 * 全局异常处理
 * </p >
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/2 0002 9:00 </p >
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private boolean showCase;

    @ExceptionHandler(value = BizException.class)
    public RespResult bizExceptionHandler(BizException e, HttpServletRequest request) {
        loggingCaseInfo(e, "自定义业务异常");
        return RespResult.exception(e.getResponseCode(), e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public RespResult bizArgumentHandler(BindException e, HttpServletRequest request) {

        loggingCaseInfo(e, "参数校验异常");
        return RespResult.exception(ResponseCode.PARAM_IS_INVALID, e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RespResult methodArgumentHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        loggingCaseInfo(e, "方法参数校验异常");
        return RespResult.exception(ResponseCode.PARAM_IS_INVALID, e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult exceptionHandler(Throwable e, HttpServletRequest request) {
        loggingCaseInfo(e, "未知异常");
        return RespResult.exception(ResponseCode.UNKNOWN_EXCEPTION, e.getMessage());
    }


    /**
     * 打印异常信息
     * 使用ExceptionUtil中的方法也能实现
     *
     * @param e
     * @param msg
     */
    private void loggingCaseInfo(Throwable e, String msg) {
        if (showCase) {
            log.error("出错了");
            log.error(msg + "===========>：" + e.getMessage());
            log.error(ExceptionUtil.getMessage(e));
        }
    }

    public boolean isShowCase() {
        return showCase;
    }

    public void setShowCase(boolean showCase) {
        this.showCase = showCase;
    }

    public GlobalExceptionHandler(Tree3Properties properties) {
        this.showCase = properties.isShowCase();
    }
}

