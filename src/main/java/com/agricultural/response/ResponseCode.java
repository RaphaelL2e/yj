package com.agricultural.response;

/**
 * 响应码 枚举类
 * Created by Lee.
 */
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
