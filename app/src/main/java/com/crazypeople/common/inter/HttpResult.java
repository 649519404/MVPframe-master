package com.crazypeople.common.inter;

/**
 * Created by 曲志强 on 2017/3/9.
 */

public class HttpResult<T> {
    public String msg;

    public int errCode;

    public T resultData;

    public void setData(T data) {
        this.resultData = data;
    }

    public T getData() {
        return resultData;
    }
}
