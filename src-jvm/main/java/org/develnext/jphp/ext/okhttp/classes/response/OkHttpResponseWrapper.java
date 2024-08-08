package org.develnext.jphp.ext.okhttp.classes.response;

import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpException;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import java.io.IOException;

public class OkHttpResponseWrapper extends BaseWrapper<OkHttpResponse> {
    public OkHttpResponseWrapper(Environment env, OkHttpResponse wrappedObject) {
        super(env, wrappedObject);
    }

    public OkHttpResponseWrapper(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public String body() throws IOException {
        return getWrappedObject().body();
    }
}
