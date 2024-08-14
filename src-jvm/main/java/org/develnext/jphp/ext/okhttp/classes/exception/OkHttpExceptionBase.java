package org.develnext.jphp.ext.okhttp.classes.exception;

import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.ext.java.JavaException;
import php.runtime.lang.BaseException;
import php.runtime.lang.BaseWrapper;
import php.runtime.memory.StringMemory;
import php.runtime.reflection.ClassEntity;

import java.io.IOException;

@Reflection.Name("OkHttpException")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpExceptionBase extends JavaException {

    protected OkHttpException okHttpException ;

    @Reflection.Property
    public String url;
    @Reflection.Property
    public String method;
    @Reflection.Property
    public String source;
    @Reflection.Property
    public String reason;
    @Reflection.Property
    public String path;
    @Reflection.Property
    public String data;
    @Reflection.Property
    public String message;

    public OkHttpExceptionBase(Environment env, Throwable throwable) {
        super(env, throwable);
        init();

    }

    public OkHttpExceptionBase(Environment env, ClassEntity clazz) {
        super(env, clazz);
        init();

    }

    protected void init(){
        okHttpException = (OkHttpException) getThrowable();
        url = okHttpException.url;
        method = okHttpException.method;
        source = okHttpException.source;
        reason = okHttpException.reason;
        path = okHttpException.path;
        data = okHttpException.data;
        message = okHttpException.message;
    }
}