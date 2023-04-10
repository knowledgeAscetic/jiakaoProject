package com.wang.jk.pojo.result;

public enum CodeMsg {
    BAD_REQUEST(400, "请求出错"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    OPERATE_OK(0, "操作成功"),
    SAVE_OK(0, "保存成功"),
    REMOVE_OK(0, "删除成功"),
    UPLOAD_OK(0, "上传成功"),

    OPERATE_ERROR(40001, "操作失败"),
    SAVE_ERROR(40002, "保存失败"),
    REMOVE_ERROR(40003, "删除失败"),
    VALIDATE_ERROR(40004, "验证失败"),
    UPLOAD_ERROR(40005, "上传失败"),

    WRONG_USERNAME(50001, "用户名不存在"),
    WRONG_PASSWORD(50002, "密码错误"),
    LOCKED_ACCOUNT(50004, "账号被锁定，无法登录"),
    WRONG_CAPTCHA(50007, "验证码错误"),

    LACK_PERMISSION(60001, "缺少相关权限"),
    EMPTY_ACCESS_TOKEN(60002, "Token不能为空，请登录后再访问"),
    TOKEN_EXPIRED(60003, "Token过期，请重新登录"),
    TOKEN_FORMAT_ERROR(60004, "Token格式错误");

    public static final int ERROR_STATUS_CODE = 400;
    public static final String ERROR_MESSAGE = "出现错误";
    public static final int OK_STATUS_CODE = 200;
    public static final String OK_MESSAGE = "请求成功";
    private final int code;
    private final String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
