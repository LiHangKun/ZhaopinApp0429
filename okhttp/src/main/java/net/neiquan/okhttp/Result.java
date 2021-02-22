package net.neiquan.okhttp;

/**
 * Description
 * Author by wangHaitao(a758277560@gmail.com)
 * Created  on 2016/5/31.
 * Version 1.0
 */
public class Result {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private int code ;
    private String  errorMessage ;
    private String  response ;
    private String msg;
    private String data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
