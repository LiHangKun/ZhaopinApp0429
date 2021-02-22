package net.neiquan.okhttp.listener;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public abstract class UploadListener implements ProgressListener, Callback{

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void onResponse(final Response response) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUISuccess(response);
            }
        });
    }

    @Override
    public void onFailure(Request request, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUIFailure(e);
            }
        });
    }

    public abstract void onUISuccess(Response response);

    public abstract void onUIFailure(Exception e);
    public abstract void onUIProgress(Progress progress);

    @Override
    public void onProgress(final Progress progress) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUIProgress( progress);
            }
        });
    }

}
