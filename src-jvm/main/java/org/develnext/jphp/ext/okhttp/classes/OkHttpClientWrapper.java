package org.develnext.jphp.ext.okhttp.classes;


import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.net.Proxy;
import java.net.ProxySelector;

@php.runtime.annotation.Reflection.Name("OkHttpClient")
@php.runtime.annotation.Reflection.Namespace("php\\demonck\\okhttp")
public class OkHttpClientWrapper extends BaseWrapper<OkHttpClient> {
    public OkHttpClientWrapper(Environment env, OkHttpClient wrappedObject) {
        super(env, wrappedObject);
    }

    public OkHttpClientWrapper(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct() {
        __wrappedObject = new OkHttpClient();
    }

    @Reflection.Signature
    public void __construct(OkHttpClient.Builder builder) {
        __wrappedObject = builder.build();
    }

    @Reflection.Signature
    public OkHttpClient.Builder newBuilder() {
        return getWrappedObject().newBuilder();
    }

    @Reflection.Signature
    public int getCallTimeoutMillis()
    {
        return getWrappedObject().callTimeoutMillis();
    }

    @Reflection.Signature
    public int getConnectTimeoutMillis()
    {
        return getWrappedObject().connectTimeoutMillis();
    }

    @Reflection.Signature
    public int getReadTimeoutMillis()
    {
        return getWrappedObject().readTimeoutMillis();
    }

    @Reflection.Signature
    public int getWriteTimeoutMillis()
    {
        return getWrappedObject().writeTimeoutMillis();
    }

    @Reflection.Signature
    public int getPingIntervalMillis()
    {
        return getWrappedObject().pingIntervalMillis();
    }

    @Reflection.Signature
    public Proxy getProxy()
    {
        return getWrappedObject().proxy();
    }

    @Reflection.Signature
    public ProxySelector getProxySelector()
    {
        return getWrappedObject().proxySelector();
    }

    @Reflection.Signature
    public CookieJar getCookieJar()
    {
        return getWrappedObject().cookieJar();
    }

    @Reflection.Signature
    public Cache getCache()
    {
        return getWrappedObject().cache();
    }

    @Reflection.Signature
    public Dns getDns()
    {
        return getWrappedObject().dns();
    }

    @Reflection.Signature
    public SocketFactory getSocketFactory()
    {
        return getWrappedObject().socketFactory();
    }

    @Reflection.Signature
    public SSLSocketFactory getSslSocketFactory()
    {
        return getWrappedObject().sslSocketFactory();
    }

    @Reflection.Signature
    public HostnameVerifier getHostnameVerifier()
    {
        return getWrappedObject().hostnameVerifier();
    }



}
