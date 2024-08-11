package org.develnext.jphp.ext.okhttp.classes.exception;

import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseException;
import php.runtime.reflection.ClassEntity;

@php.runtime.annotation.Reflection.Name("OkHttpBase")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpBase extends BaseException {
    public OkHttpBase(Environment env) {
        super(env);
    }

    public OkHttpBase(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }
}
