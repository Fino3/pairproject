package com.example.back.common;

import lombok.Data;

@Data
public class Result {
    private int code;
    private Object data;
    private String msg;

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public Result(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    public static Result create(int code, Object data, String msg){
        return new Result(code, data,msg);
    }
    public static Result create(int code, Object data){
        return new Result(code, data,null);
    }
    public static Result create(int code){
        return new Result(code, null,null);
    }

    public static int SUCCESS = 1000;
    public static int ERROR = 1001;
    public static int NO_API_ERROR = 1002;
}
