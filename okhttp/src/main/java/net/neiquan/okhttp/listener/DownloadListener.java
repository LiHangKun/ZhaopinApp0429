package net.neiquan.okhttp.listener;

import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Description 下载监听
 * Author by wangHaitao(a758277560@gmail.com)
 * Created  on 2016/8/24 11:16
 * Version V1.0   
 */
public abstract class DownloadListener implements ProgressListener, Callback {

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private String mDestFileDir;
    private String mDestFileName;

    public DownloadListener(String mDestFileDir, String mDestFileName) {
        this.mDestFileDir = mDestFileDir;
        this.mDestFileName = mDestFileName;
    }

    @Override
    public void onResponse(final Response response) {

        File file = null;
        try {
            file = saveFile(response);
        } catch (final IOException e) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onUIFailure(e);
                }
            });
        }

        final File newFile = file;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUISuccess(newFile);
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

    public abstract void onUISuccess(File file);

    public abstract void onUIFailure(Exception e);
    public abstract void onUIProgress(Progress progress);

    public File saveFile(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File dir = new File(mDestFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, mDestFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void onProgress(final Progress progress) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUIProgress(progress);
            }
        });
    }


}
