package com.ssm.maven.core.util;

public class LayuiRtn {
    private int code;
    private String msg;
    private long count;
    private Object data;

    public LayuiRtn() {
    }

    public LayuiRtn(int code) {
        this.code = code;
    }

    public LayuiRtn(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JackJson.fromObjectToJson(this);
    }
}
