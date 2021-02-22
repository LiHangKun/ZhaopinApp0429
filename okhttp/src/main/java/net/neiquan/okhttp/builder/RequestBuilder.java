package net.neiquan.okhttp.builder;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description 请求构建
 * Author by wangHaitao(a758277560@gmail.com)
 * Created  on 2016/8/24 11:43
 * Version V1.0   
 */
public abstract class RequestBuilder<T> {

    protected String url;
    protected Map<String, String> params;
    protected Object tag;

    abstract Call enqueue(Callback callback);

    abstract Response execute() throws IOException;

    protected void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    protected void appendParams(Builder builder, Map<String, String> params) {

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }

    protected String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
