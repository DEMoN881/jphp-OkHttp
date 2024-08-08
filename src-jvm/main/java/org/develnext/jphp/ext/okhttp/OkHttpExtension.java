package org.develnext.jphp.ext.okhttp;

import okhttp3.Address;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.develnext.jphp.ext.okhttp.classes.AddressWrapper;
import org.develnext.jphp.ext.okhttp.classes.OkHttpClientWrapper;
import org.develnext.jphp.ext.okhttp.classes.RequestWrapper;
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

        registerWrapperClass(scope, Address.class, AddressWrapper.class);
        registerWrapperClass(scope, OkHttpClient.class, OkHttpClientWrapper.class);
        registerWrapperClass(scope, Request.class, RequestWrapper.class);



    }
}
