package org.develnext.jphp.ext.okhttp;

import okhttp3.Address;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.develnext.jphp.ext.okhttp.classes.OkHttpClientWrapper;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpExceptionWrapper;
import org.develnext.jphp.ext.okhttp.classes.response.OkHttpResponse;
import org.develnext.jphp.ext.okhttp.classes.response.OkHttpResponseWrapper;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

import java.io.IOException;


public class OkHttpExtension extends Extension
{
    public static final String NS = "php\\demonck\\okhttp";

    @Override
    public Status getStatus()
    {
        return Status.BETA;
    }
    @Override
    public void onRegister(CompileScope scope)
    {

        registerWrapperClass(scope, OkHttpExtension.class, OkHttpExceptionWrapper.class);
        registerWrapperClass(scope, OkHttpResponse.class, OkHttpResponseWrapper.class);
        registerWrapperClass(scope, OkHttpClient.class, OkHttpClientWrapper.class);



    }
}
