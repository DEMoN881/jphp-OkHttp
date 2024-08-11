package org.develnext.jphp.ext.okhttp.classes.exception;

import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseException;
import php.runtime.lang.exception.BaseBaseException;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("OkHttpException")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpException extends BaseException {
    private String url;
    private String method;
    private String source;
    private String reason;
    private String path;

    public OkHttpException(Environment env, String url, String method, String source, String reason, String path) {
        super(env);
        this.url = url;
        this.method = method;
        this.source = source;
        this.reason = reason;
        this.path = path;
    }
    public OkHttpException(Environment env) {
        super(env);
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

    public String getPath() {
        return path;
    }
}