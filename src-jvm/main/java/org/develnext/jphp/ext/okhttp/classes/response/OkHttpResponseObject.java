package org.develnext.jphp.ext.okhttp.classes.response;

import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseObject;
import php.runtime.memory.StringMemory;
import php.runtime.reflection.ClassEntity;

import java.io.IOException;
import java.util.List;

@Reflection.Name("OkHttpResponse")
@Reflection.Namespace(OkHttpExtension.NS + "\\response")
public class OkHttpResponseObject extends BaseObject {
    private Response response;

    public OkHttpResponseObject(Environment env, Response response) {
        super(env);
        this.response = response;
    }

    protected OkHttpResponseObject(ClassEntity entity) {
        super(entity);
    }

    public OkHttpResponseObject(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public Memory body(){
       try {
           System.out.println("body");
           ResponseBody responseBody = response.body();

           System.out.println(responseBody.string());
           return new StringMemory(responseBody.string());
       }catch (IOException e) {
           e.printStackTrace();

       }
       return null;
    }

    @Reflection.Signature
    public int statusCode() {
        return response.code();
    }

    @Reflection.Signature
    public String statusMessage() {
        return response.message();
    }

    @Reflection.Signature
    public Headers headers() {
        return response.headers();
    }

    @Reflection.Signature
    public String header(String name) {
        return response.header(name);
    }

    @Reflection.Signature
    public String contentType() {
        return response.header("Content-Type");
    }

    @Reflection.Signature
    public long contentLength() {
        return response.body() != null ? response.body().contentLength() : -1;
    }

    @Reflection.Signature
    public String cookie(String name) {
        List<String> cookies = response.headers("Set-Cookie");
        for (String cookie : cookies) {
            if (cookie.startsWith(name + "=")) {
                return cookie;
            }
        }
        return null;
    }

    @Reflection.Signature
    public List<String> cookies() {
        return response.headers("Set-Cookie");
    }

    @Reflection.Signature
    public boolean isSuccess() {
        int code = response.code();
        return code >= 200 && code <= 399;
    }

    @Reflection.Signature
    public boolean isFail() {
        return response.code() >= 400;
    }

    @Reflection.Signature
    public boolean isError() {
        return response.code() >= 400;
    }

    @Reflection.Signature
    public boolean isNotFound() {
        return response.code() == 404;
    }

    @Reflection.Signature
    public boolean isAccessDenied() {
        return response.code() == 403;
    }

    @Reflection.Signature
    public boolean isInvalidMethod() {
        return response.code() == 405;
    }

    @Reflection.Signature
    public boolean isServerError() {
        return response.code() >= 500;
    }
}
