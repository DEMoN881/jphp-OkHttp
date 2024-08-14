package org.develnext.jphp.ext.okhttp.classes.exception;


public class OkHttpException extends Throwable{


    public String url;

    public String method;

    public String source;

    public String reason;

    public String path;

    public String data;

    public String message;

    public OkHttpException(String url, String method, String source, String reason, String path, String message) {
        super(message);

        this.url = url;
        this.method = method;
        this.source = source;
        this.reason = reason;
        this.path = path;
        this.message = message;
    }

    public OkHttpException(String path, String method, String data, String message) {
        super(message);

        this.method = method;
        this.data = data;
        this.path = path;
        this.message = message;
    }
}
