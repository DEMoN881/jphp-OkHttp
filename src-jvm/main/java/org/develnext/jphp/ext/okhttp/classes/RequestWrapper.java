package org.develnext.jphp.ext.okhttp.classes;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;
import java.util.List;

@php.runtime.annotation.Reflection.Name("Request")
@php.runtime.annotation.Reflection.Namespace("php\\demonck\\okhttp")
public class RequestWrapper extends BaseWrapper<Request> {
    public RequestWrapper(Environment env, Request wrappedObject) {
        super(env, wrappedObject);
    }

    public RequestWrapper(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct(String url) {
        __wrappedObject = new Request.Builder().url(url).build();
    }

    @Reflection.Signature
    public HttpUrl getUrl() {
        return getWrappedObject().url();
    }

    @Reflection.Signature
    public String getMethod() {
        return getWrappedObject().method();
    }

    @Reflection.Signature
    public Headers getHeaders() {
        return getWrappedObject().headers();
    }

    @Reflection.Signature
    public String getHeader(String name) {
        return getWrappedObject().header(name);
    }

    @Reflection.Signature
    public List<String> getHeaders(String headers) {
        return getWrappedObject().headers(headers);
    }

    @Reflection.Signature
    public RequestBody getBody() {
        return getWrappedObject().body();
    }

    @Reflection.Signature
    public boolean isHttps() {
        return getWrappedObject().isHttps();
    }

}
