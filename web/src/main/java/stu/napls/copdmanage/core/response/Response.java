package stu.napls.copdmanage.core.response;

import stu.napls.copdmanage.core.constant.Code;

import java.io.Serializable;

/**
 * @author Yepeng Ding
 */
public class Response implements Serializable {

//    private static int SUCCESS = 200;
//    private static int FAILURE = -1;

    private int code;
    private String message;
    private Object data;

    private Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static final Response success(Object data) {
        return new Response(Code.SUCCESS, "ok", data);
    }

    /**
     * @param message
     * @return
     */
    public static final Response success(String message) {
        return new Response(Code.SUCCESS, message, null);
    }

    public static final Response success(String message, Object data) {
        return new Response(Code.SUCCESS, message, data);
    }

    public static final Response failure(int code, String message) {
        return new Response(code, message, null);
    }

    public static final Response failure(String message) {
        return failure(Code.FAILURE, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "{code:\"" + code + "\", message:\"" + message + "\", data:\"" + (data != null ? data.toString() : null) + "\"}";
    }

}
