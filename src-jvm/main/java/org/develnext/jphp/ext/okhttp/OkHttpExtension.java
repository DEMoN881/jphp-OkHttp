package org.develnext.jphp.ext.okhttp;

import okhttp3.OkHttpClient;
import org.develnext.jphp.ext.okhttp.classes.OkHttpClientWrapper;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpBase;
import org.develnext.jphp.ext.okhttp.classes.response.OkHttpResponseObject;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;


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

        registerClass(scope, OkHttpExtension.class);
        registerClass(scope, OkHttpResponseObject.class);
        registerWrapperClass(scope, OkHttpClient.class, OkHttpClientWrapper.class);
        registerClass(scope, OkHttpBase.class);


    }
}
