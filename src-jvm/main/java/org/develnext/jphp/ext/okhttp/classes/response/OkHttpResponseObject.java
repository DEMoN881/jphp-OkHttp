package org.develnext.jphp.ext.okhttp.classes.response;

import okhttp3.Response;
import okhttp3.ResponseBody;
import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseObject;
import php.runtime.reflection.ClassEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Reflection.Name("OkHttpResponse")
@Reflection.Namespace(OkHttpExtension.NS + "\\response")
public class OkHttpResponseObject extends BaseObject {

    private Response response;

    @Reflection.Property
    public String body;
    @Reflection.Property
    public int code;
    @Reflection.Property
    public String statusMessage;
    @Reflection.Property
    public Map<String, List<String>> headers;
    @Reflection.Property
    public long contentLength;


    public OkHttpResponseObject(Environment env, Response response) throws IOException {
        super(env);

        ResponseBody body = response.body();

        this.contentLength =response.body() != null ? response.body().contentLength() : -1;
        this.body = body.string();
        this.response = response;
        this.code = response.code();
        this.statusMessage = response.message();
        this.headers = response.headers().toMultimap();



    }
    @Override
    public String toString()
    {
        return "OkHttpResponse Make Demonck && Koso-Group: " + this.body + " " + this.code + " " + this.statusMessage + " " + this.headers + " " + this.contentLength;
    }

    protected OkHttpResponseObject(ClassEntity entity) {
        super(entity);
    }

    public OkHttpResponseObject(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

}
