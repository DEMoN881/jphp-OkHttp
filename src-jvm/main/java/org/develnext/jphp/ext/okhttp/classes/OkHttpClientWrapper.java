package org.develnext.jphp.ext.okhttp.classes;

import okhttp3.*;
import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpException;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpExceptionBase;
import org.develnext.jphp.ext.okhttp.classes.response.OkHttpResponseObject;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Reflection.Name("OkHttpClientWrapper")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpClientWrapper extends BaseWrapper<OkHttpClient> {

    @Reflection.Property
    public String userAgent = "JPHP-OkHttp/1.0";

    @Reflection.Property
    public String url;

    @Reflection.Property
    public String requestType;

    @Reflection.Property
    public int connectTimeoutMs = 3000;

    @Reflection.Property
    public int readTimeoutMs = 3000;

    @Reflection.Property
    public int writeTimeoutMs = 3000;

    @Reflection.Property
    public boolean followRedirects = true;

    @Reflection.Property
    public boolean retryOnConnectionFailure = false;

    @Reflection.Property
    public int callTimeoutMs = 3000;

    @Reflection.Property
    public boolean followSslRedirects = true;

    @Reflection.Property
    public HashMap<String, String> headers = new HashMap<String, String>();

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

    private OkHttpResponseObject _execute(Request request) throws OkHttpException {

        try {
            OkHttpClient.Builder clientBuilder = getWrappedObject().newBuilder();
            clientBuilder.retryOnConnectionFailure(retryOnConnectionFailure);
            clientBuilder.followRedirects(followRedirects);
            clientBuilder.followSslRedirects(followSslRedirects);
            clientBuilder.callTimeout(callTimeoutMs, TimeUnit.MILLISECONDS);
            clientBuilder.addInterceptor(chain -> {
                Request original = chain.request();
                Request request1 = original.newBuilder()
                        .header("User-Agent", userAgent)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request1);
            });


            //to-do fix ebanay java 8 error here
            if (proxy != null) {
                clientBuilder.proxy(proxy);
                if (proxyUsername != null && proxyPassword != null) {
                    clientBuilder.proxyAuthenticator(new Authenticator() {
                        @Override
                        public Request authenticate(Route route, Response response)  {
                            String credential = Credentials.basic(proxyUsername, proxyPassword);
                            return response.request().newBuilder()
                                    .header("Proxy-Authorization", credential)
                                    .build();
                        }
                    });
                }
            }

            clientBuilder
                    .connectTimeout(connectTimeoutMs, TimeUnit.MILLISECONDS)
                    .readTimeout(readTimeoutMs, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeoutMs, TimeUnit.MILLISECONDS);

            OkHttpClient client = clientBuilder.build();

            Response response = client.newCall(request).execute();
            System.out.println("response: " + response);
            return new OkHttpResponseObject(getEnvironment(), response);
        } catch (Exception e) {
            throw new OkHttpException(request.url().toString(), request.method(), e.getMessage(), e.getClass().getName(), request.url().encodedPath(), e.getMessage());

        }
    }


    @Reflection.Signature
    public OkHttpResponseObject execute(String method,String path, String data) throws OkHttpException
    {
        System.out.println("method: " + method);
        Request request = _createRequest(path, method, data);
        System.out.println("request: " + request);
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

    private Request _createRequest(String path, String method, String data) throws OkHttpException {
        Request.Builder builder = new Request.Builder();
        method = method.toUpperCase();

        switch (method) {
            case "GET":
            case "HEAD":
                if (data != null && !data.isEmpty()) {
                   // to-do new  throw new OkHttpExceptionBase(getEnvironment(), path, method, data);
                }
                builder.method(method, null);
                break;

            case "POST":
            case "PUT":
            case "PATCH":
            case "TRACE":
            case "OPTIONS":
            case "CONNECT":
            case "DELETE":
                RequestBody requestBody = data != null ? RequestBody.create(data, _getMediaType()) : null;
                builder.method(method, requestBody);
                break;

            default:
                throw new OkHttpException(path, method, data, "Unsupported method: "+ path);
        }
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        return builder
                .url(url + path)
                .build();
    }

    private MediaType _getMediaType() {

         if (requestType == null) {
              return MediaType.parse("application/json; charset=utf-8");
         }
           return MediaType.parse(requestType);

    }

    @Reflection.Signature
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Reflection.Signature
    public void removeHeader(String key) {
        headers.remove(key);
    }

    @Reflection.Signature
    public boolean hasHeader(String key) {
        return headers.containsKey(key);
    }

}