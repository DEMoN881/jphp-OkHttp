package org.develnext.jphp.ext.okhttp.classes;

import okhttp3.*;
import org.develnext.jphp.ext.okhttp.OkHttpExtension;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpBase;
import org.develnext.jphp.ext.okhttp.classes.exception.OkHttpException;
import org.develnext.jphp.ext.okhttp.classes.response.OkHttpResponseObject;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.memory.ObjectMemory;
import php.runtime.reflection.ClassEntity;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import php.runtime.ext.core.classes.WrapPromise;

@Reflection.Name("OkHttpClient")
@Reflection.Namespace(OkHttpExtension.NS)
public class OkHttpClientWrapper extends BaseWrapper<OkHttpClient> {

    @Reflection.Property
    public static final String JSON = "JSON";

    @Reflection.Property
    public static final String URLENCODE = "URLENCODE";

    @Reflection.Property
    public static final String TEXT = "TEXT";

    @Reflection.Property
    public static final String MULTIPART = "MULTIPART";

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
                        public Request authenticate(Route route, Response response) throws IOException {
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

            return new OkHttpResponseObject(getEnvironment(), response);
        } catch (IOException e) {
            throw new OkHttpException(getEnvironment());
        }
    }

    @Reflection.Signature
    public OkHttpResponseObject get(String path) throws OkHttpException {
         return execute(path, "GET", null);
    }

    @Reflection.Signature
    public OkHttpResponseObject post(String path, String body) throws OkHttpException {
        return execute(path, "POST", body);
    }

    @Reflection.Signature
    public OkHttpResponseObject put(String path, String body) throws OkHttpException {
        return execute(path, "PUT", body);
    }

    @Reflection.Signature
    public OkHttpResponseObject head(String path) throws OkHttpException {
        return execute(path, "HEAD", null);
    }

    @Reflection.Signature
    public OkHttpResponseObject patch(String path, String body) throws OkHttpException {
        return execute(path, "PATCH", body);
    }

    @Reflection.Signature
    public OkHttpResponseObject trace(String path, String body) throws OkHttpException {
        return execute(path, "TRACE", body);
    }

    @Reflection.Signature
    public OkHttpResponseObject connect(String path, String body) throws OkHttpException {
        return execute(path, "CONNECT", body);
    }

    @Reflection.Signature
    public OkHttpResponseObject options(String path, String body) throws OkHttpException {
        return execute(path, "OPTIONS", body);
    }


    @Reflection.Signature
    public OkHttpResponseObject delete(String path, String body) throws OkHttpException {
        return execute(path, "DELETE", body);
    }

    @Reflection.Signature
    @Reflection.Nullable
    public OkHttpResponseObject execute(String method,String path, String body) throws OkHttpException
    {
        Request request = _createRequest(path, method, body);
        return _execute(request);
    }

    @Reflection.Signature
    public WrapPromise asyncExecute(String method, String path, String body) {

        Environment env = getEnvironment();
        WrapPromise promise = new WrapPromise(env, new php.runtime.common.Callback<Memory, Memory[]>() {
            @Override
            public Memory call(Memory[] args) {
                try {
                    new Thread(()->{

                        Request request = _createRequest(path, method, body);
                        OkHttpResponseObject response = _execute(request);

                            try {
                                    args[0].toInvoker(env).call(new Memory[]{ObjectMemory.valueOf(response)});
                                } catch (Throwable e) {
                                    try {
                                        args[1].toInvoker(env).call(new Memory[]{ObjectMemory.valueOf(new OkHttpBase(env))});
                                    } catch (Throwable ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            }).start();

                } catch (Throwable e) {
                    try {
                        args[1].toInvoker(env).call(new Memory[]{ObjectMemory.valueOf(new OkHttpBase(env))});
                    } catch (Throwable ex) {
                        throw  new RuntimeException(ex);
                    }
                }
                return Memory.NULL;
            }
        });


       return promise;
    }

    @Reflection.Signature
    public WrapPromise asyncGet(String path) {
        return asyncExecute("GET", path, null);
    }

    @Reflection.Signature
    public WrapPromise asyncPost(String path, String body) {
        return asyncExecute("POST", path, body);
    }

    @Reflection.Signature
    public WrapPromise asyncPut(String path, String body) {
        return asyncExecute("PUT", path, body);
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
        Request.Builder builder = new Request.Builder();
        method = method.toUpperCase();

        switch (method) {
            case "GET":
            case "HEAD":
                if (body != null && !body.isEmpty()) {
                    throw new OkHttpException(getEnvironment());
                }
                builder.method("GET", null);
                break;

            case "POST":
            case "PUT":
            case "PATCH":
            case "TRACE":
            case "OPTIONS":
            case "CONNECT":
            case "DELETE":
                RequestBody requestBody = body != null ? RequestBody.create(body, _getMediaType()) : null;
                builder.method(method, requestBody);
                break;

            default:
                throw new OkHttpException(getEnvironment());
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
                if (requestType == null) {
                    return MediaType.parse("application/json; charset=utf-8");
                }
                return MediaType.parse(requestType);
        }
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