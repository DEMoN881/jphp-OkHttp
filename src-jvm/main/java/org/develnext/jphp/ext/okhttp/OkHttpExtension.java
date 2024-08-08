package org.develnext.jphp.ext.okhttp;

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


    }
}
