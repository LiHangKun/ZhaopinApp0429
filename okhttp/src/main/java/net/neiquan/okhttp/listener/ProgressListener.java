package net.neiquan.okhttp.listener;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public interface ProgressListener {
    void onProgress(Progress progress);
    void onResponse(Response response);
    void onFailure(Request request, IOException e);
}