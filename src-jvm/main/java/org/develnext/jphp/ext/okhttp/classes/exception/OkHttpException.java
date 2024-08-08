package org.develnext.jphp.ext.okhttp.classes.exception;

import java.io.IOException;

public class OkHttpException extends IOException {
    private String url;
    private String method;
    private String source;
    private String reason;
    private String path;

    public OkHttpException() {
        super();
    }

    public OkHttpException(String message) {
        super(message);
    }

    public OkHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public OkHttpException(Throwable cause) {
        super(cause);
    }

    public OkHttpException(String message, String url, String method, String source, String reason,String path) {
        super(message);
        this.url = url;
        this.method = method;
        this.source = source;
        this.reason = reason;
        this.path = path;
    }

    public OkHttpException(String message, Throwable cause, String url, String method, String source, String reason, String path) {
        super(message, cause);
        this.url = url;
        this.method = method;
        this.source = source;
        this.reason = reason;
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getSource() {
        return source;
    }

    public String getReason() {
        return reason;
    }

    public String getPath()
    {
        return path;
    }
}