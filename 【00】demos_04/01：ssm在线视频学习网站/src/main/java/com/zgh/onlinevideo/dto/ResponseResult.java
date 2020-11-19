package com.zgh.onlinevideo.dto;

/**
 * 页面返回的数据类型
 */
public class ResponseResult<T> {

    // 响应码
    private int rcode;

    // 数据
    private T data;

    // 消息提示
    private String message;

    public ResponseResult() {
    }

    public ResponseResult(int rcode, T data, String message) {
        this.rcode = rcode;
        this.data = data;
        this.message = message;
    }

    public ResponseResult(int rcode, String message) {
        this.rcode = rcode;
        this.message = message;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
