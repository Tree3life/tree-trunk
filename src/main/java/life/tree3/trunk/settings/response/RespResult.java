package life.tree3.trunk.settings.response;

import lombok.*;

import java.io.Serializable;

/**
 * <p>描述:
 * 统一封装的请求结果
 * </p >
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 23:29 </p >
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class RespResult implements Serializable {
    private static final long serialVersionUID = 37205949387916293L;
    private Integer code;
    private String message;
    private Object data;

    private RespResult(ResponseCode responseCode) {
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    private RespResult(ResponseCode responseCode, Object data) {
        this.code = responseCode.code();
        this.message = responseCode.message();
        this.data = data;
    }

    /**
     * 异常响应结果
     * @param errorMsg
     */
    private RespResult(String errorMsg) {
        this.code = ResponseCode.SERVER_INTERNAL_EXCEPTION.code();
        this.message = errorMsg;
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static RespResult success(Object data) {
        return new RespResult(ResponseCode.SUCCESS, data);
    }

    /**
     * 失败结果
     *
     * @param code
     * @param data
     * @return
     */
    public static RespResult failure(ResponseCode code, Object data) {
        return new RespResult(code, data);
    }
    public static RespResult failure(ResponseCode code) {
        return new RespResult(code);
    }

    /**
     * 异常处理结果
     *
     * @param responseCode
     * @param errorMsg
     * @return
     */
    public static RespResult exception(ResponseCode responseCode, String errorMsg) {
        return new RespResult(responseCode, errorMsg);
    }

    /**
     * 异常处理结果
     */
    public static RespResult exception(String errorMsg) {
        return new RespResult(errorMsg);
    }

}
