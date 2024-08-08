package org.develnext.jphp.ext.okhttp.classes;


import okhttp3.*;
import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpException;
import org.develnext.jphp.ext.okhttp.classes.response.OkHttpResponse;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;

@Reflection.Name("OkHttpClient")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpClientWrapper extends BaseWrapper<OkHttpClient> {

    @Reflection.Property
    public String url;

    @Reflection.Property
    public String requestType;


    private Proxy proxy;


    private String proxyUsername;


    private String proxyPassword;

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

    private OkHttpResponse _execute(Request request)
    {
        try {
            return new OkHttpResponse(getWrappedObject().newCall(request).execute());
        } catch (IOException e) {
            new OkHttpExtension();
        }
        return null;
    }


    @Reflection.Signature
    public OkHttpResponse get(String path) throws OkHttpException {
        Request request = _createRequest(path, "GET", null);

        return _execute(request);
    }

    @Reflection.Signature
    public OkHttpResponse post(String path, String body) throws OkHttpException {
        Request request = _createRequest(path, "POST", body);

        return _execute(request);
    }

    @Reflection.Signature
    public void setProxy(String host, int port) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }

    @Reflection.Signature
    public void setProxyWithAuth(String host, int port, String username, String password) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        this.proxyUsername = username;
        this.proxyPassword = password;
    }

    private Request _createRequest(String path, String method, String body) throws OkHttpException {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (proxy != null) {
            clientBuilder.proxy(proxy);

            if (proxyUsername != null && proxyPassword != null) {
                clientBuilder.proxyAuthenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic(proxyUsername, proxyPassword);
                        return response.request().newBuilder()
                                .header("Proxy-Authorization", credential).build();
                    }
                });
            }
        }

        OkHttpClient client = clientBuilder.build();

        Request.Builder builder = new Request.Builder();
        method = method.toUpperCase();

        switch (method) {
            case "GET":
                if (body != null && !body.isEmpty()) {
                    throw new OkHttpException("GET method must not have a request body.");
                }
                builder.method("GET", null);
                break;

            case "POST":
            case "PUT":
            case "PATCH":
                RequestBody requestBody = body != null ? RequestBody.create(body, _getMediaType()) : null;
                builder.method(method, requestBody);
                break;

            case "DELETE":
                RequestBody deleteBody = body != null ? RequestBody.create(body, _getMediaType()) : null;
                builder.method("DELETE", deleteBody);
                break;

            default:
                throw new OkHttpException("HTTP method not allowed: " + method);
        }

        return builder
                .url(url + path)
                .build();
    }


    private MediaType _getMediaType() {
        switch (requestType.toUpperCase()) {
            case "JSON":
                return MediaType.parse("application/json; charset=utf-8");
            case "URLENCODE":
                return MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
            case "TEXT":
                return MediaType.parse("text/plain; charset=utf-8");
            case "MULTIPART":
                return MediaType.parse("multipart/form-data");
            default:
                if(requestType == null){
                    return MediaType.parse("application/json; charset=utf-8");
                }
                return MediaType.parse(requestType);

        }
    }


}
