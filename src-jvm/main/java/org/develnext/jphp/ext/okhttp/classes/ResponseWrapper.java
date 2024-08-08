package org.develnext.jphp.ext.okhttp.classes;

import okhttp3.Response;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

@php.runtime.annotation.Reflection.Name("Response")
@php.runtime.annotation.Reflection.Namespace("php\\demonck\\okhttp")
public class ResponseWrapper extends BaseWrapper<Response> {
    public ResponseWrapper(Environment env, Response wrappedObject) {
        super(env, wrappedObject);
    }

    public ResponseWrapper(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct() {
        __wrappedObject = new Response.Builder().build();
    }

    @Reflection.Signature

}
