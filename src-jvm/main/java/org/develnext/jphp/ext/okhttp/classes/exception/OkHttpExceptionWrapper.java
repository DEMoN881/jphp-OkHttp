package org.develnext.jphp.ext.okhttp.classes.exception;

import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("OkHttpException")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpExceptionWrapper extends BaseWrapper<OkHttpException> {
    public OkHttpExceptionWrapper(Environment env, OkHttpException wrappedObject) {
        super(env, wrappedObject);
    }

    public OkHttpExceptionWrapper(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public String getUrl() {
        return getWrappedObject().getUrl();
    }

    @Reflection.Signature
    public String getMethod() {
        return getWrappedObject().getMethod();
    }

    @Reflection.Signature
    public String getSource() {
        return getWrappedObject().getSource();
    }

    @Reflection.Signature
    public String getReason() {
        return getWrappedObject().getReason();
    }

    @Reflection.Signature
    public String getPath() {
        return getWrappedObject().getPath();
    }

}
