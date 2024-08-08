package org.develnext.jphp.ext.okhttp.classes;

import okhttp3.*;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;

@php.runtime.annotation.Reflection.Name("Address")
@php.runtime.annotation.Reflection.Namespace("php\\demonck\\okhttp")
public class AddressWrapper extends BaseWrapper<Address> {
    public AddressWrapper(Environment env, Address wrappedObject) {
        super(env, wrappedObject);
    }

    public AddressWrapper(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct(String uriHost, int uriPort, Dns dns, SocketFactory socketFactory, SSLSocketFactory sslSocketFactory, HostnameVerifier hostnameVerifier, CertificatePinner certificatePinner, Authenticator proxyAuthenticator, Proxy proxy, List<Protocol> protocols, List<ConnectionSpec> connectionSpecs, ProxySelector proxySelector) {

        __wrappedObject = new Address(uriHost, uriPort,  dns, socketFactory, sslSocketFactory, hostnameVerifier, certificatePinner, proxyAuthenticator, proxy, protocols, connectionSpecs, proxySelector);
    }


    @Reflection.Signature
    public HttpUrl getHttpUrl(){
        return getWrappedObject().url();
    }

    @Reflection.Signature
    public Dns getDns() {
        return getWrappedObject().dns();
    }

    @Reflection.Signature
    public SocketFactory getSocketFactory() {
        return getWrappedObject().socketFactory();
    }

    @Reflection.Signature
    public SSLSocketFactory getSslSocketFactory() {
        return getWrappedObject().sslSocketFactory();
    }

    @Reflection.Signature
    public HostnameVerifier getHostnameVerifier() {
        return getWrappedObject().hostnameVerifier();
    }
    @Reflection.Signature
    public CertificatePinner getCertificatePinner() {
        return getWrappedObject().certificatePinner();
    }

    @Reflection.Signature
    public Authenticator getProxyAuthenticator() {
        return getWrappedObject().proxyAuthenticator();
    }

    @Reflection.Signature
    public Proxy getProxy() {
        return getWrappedObject().proxy();
    }

    @Reflection.Signature
    public List<Protocol> getProtocols() {
        return getWrappedObject().protocols();
    }

    @Reflection.Signature
    public List<ConnectionSpec> getConnectionSpecs() {
        return getWrappedObject().connectionSpecs();
    }

    @Reflection.Signature
    public ProxySelector getProxySelector() {
        return getWrappedObject().proxySelector();
    }



}
