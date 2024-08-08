package org.develnext.jphp.ext.okhttp.classes.response;

import okhttp3.Response;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.util.List;

public class OkHttpResponse {
    private Response response;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    public String body() throws IOException {
        ResponseBody responseBody = response.body();
        return responseBody != null ? responseBody.string() : null;
    }

    public int statusCode() {
        return response.code();
    }

    public String statusMessage() {
        return response.message();
    }

    public Headers headers() {
        return response.headers();
    }

    public String header(String name) {
        return response.header(name);
    }

    public String contentType() {
        return response.header("Content-Type");
    }

    public long contentLength() {
        return response.body() != null ? response.body().contentLength() : -1;
    }

    public String cookie(String name) {
        List<String> cookies = response.headers("Set-Cookie");
        for (String cookie : cookies) {
            if (cookie.startsWith(name + "=")) {
                return cookie;
            }
        }
        return null;
    }

    public List<String> cookies() {
        return response.headers("Set-Cookie");
    }

    public boolean isSuccess() {
        int code = response.code();
        return code >= 200 && code <= 399;
    }

    public boolean isFail() {
        return response.code() >= 400;
    }

    public boolean isError() {
        return response.code() >= 400;
    }

    public boolean isNotFound() {
        return response.code() == 404;
    }

    public boolean isAccessDenied() {
        return response.code() == 403;
    }

    public boolean isInvalidMethod() {
        return response.code() == 405;
    }

    public boolean isServerError() {
        return response.code() >= 500;
    }
}
