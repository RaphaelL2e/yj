package com.agricultural.exception;


import com.agricultural.enums.ErrorCodeEnum;

/**
 * @author:leeyf
 * @create: 2019-01-29 17:54
 * @Description: 用户异常
 */
public class UserException extends SysException{
    public UserException(Integer code, String msg){
        super(code,msg);
    }

    public UserException(){
    }

    public UserException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum);
    }
}