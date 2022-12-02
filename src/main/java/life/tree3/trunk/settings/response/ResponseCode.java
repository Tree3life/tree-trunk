package life.tree3.trunk.settings.response;

/**
 * <p>描述: 状态码;<br/>
 * 200：成功；<br/>
 * 参数错误：1001-1999<br/>
 * 用户错误：2001-2999<br/>
 * 业务异常：3001-3999<br/>
 * 客户端错误：4001-4999<br/>
 * 服务端错误：5001-5999<br/>
 * 服务端未主动进行处理的异常：-5000</p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/1 0001 23:23 </p >
 */
public enum ResponseCode {
    SUCCESS(2000, "成功"),

    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    USER_NOT_LOGGED_IN(2001, "用户未登录，请登录！"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误！"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用！"),
    USER_NOT_EXIST(2004, "用户不存在！"),
    USER_ALREADY_EXISTED(2005, "用户已存在！"),
    PERMISSION_DENIED(2006, "权限不足！"),
    Login_Failure(2009, "登录失败！"),
    BIZ_ERROR(3001, "业务逻辑异常！"),
    INVALID_TOKEN(4001, "token无效！"),
    AccessDenied(4002, "服务器拒绝响应！"),
    UNKNOWN_EXCEPTION(-5000, "不确定的异常类型!"),
    SHIRO_EXCEPTION(5001, "shiro异常"),
    ASSERT_EXCEPTION(5002, "Assert断言，抛出异常"),
    SERVER_INTERNAL_EXCEPTION(5099, "服务器异常"),
    MEANINGLESS_USE(5100, "无意义的操作");


    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
