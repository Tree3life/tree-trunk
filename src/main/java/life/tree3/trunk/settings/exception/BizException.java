package life.tree3.trunk.settings.exception;

import life.tree3.trunk.settings.response.ResponseCode;

/**
 * <p>描述: 自定义业务相关的异常 </p>
 */
public class BizException extends RuntimeException {

    private ResponseCode responseCode;


    public BizException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public BizException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
