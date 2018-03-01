package com.freework.weixin_backend.utils;

/**
 * 错误码定义
 */
public enum ErrorCode {

    SUCCEED(0,"succeed"),
    ERR_UNKNOWN(1,"未知错误"),
    ERR_PARAMTER(2,"参数错误"),
    ERR_DECODE(3,"解密失败"),
    ERR_DATABASE(4,"数据库错误");

    //错误码,0 表示成功
    private int errorCode;

    //错误码对应的描述
    private String errorDesc;

    // 构造方法
    private ErrorCode(int code,String desc) {
        this.errorDesc = desc;
        this.errorCode = code;
    }

    // 普通方法
    public static String getErrorDesc(int code) {
        for (ErrorCode c : ErrorCode.values()) {
            if (c.getErrorCode() == code) {
                return c.errorDesc;
            }
        }
        return null;
    }

    public  String getErrorDesc() {
        return this.errorDesc;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String toString() {
        return this.errorCode+"_"+this.errorDesc;
    }
}
