package nyc.c4q;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import okio.BufferedSink;

public class HttpUtil {

    private OkHttpClient client;
    private Request.Builder builder;

    public void get(String url, HttpCallback cb) {
        call("GET", url, cb);
    }

    public void post(String url, HttpCallback cb) {
        call("POST", url, cb);
    }

    private void call(String method, String url, final HttpCallback cb) {
        Request request = builder.url(url).method(method, method.equals("GET") ? null : new RequestBody() {

            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {

            }
        }).build();

        client.newCall(request).enqueue(new Callback() {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            @Override
            public void onFailure(Request request,final IOException throwable) {
                mainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        cb.onFailure(null, throwable);
                    }
                });

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                mainHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (!response.isSuccessful()) {
                            cb.onFailure(response, null);
                            return;
                        }
                        cb.onSuccess(response);
                    }
                });

            }
        });
    }


    public interface HttpCallback  {

        /**
         * called when the server response was not 2xx or when an exception was thrown in the process
         * @param response - in case of server error (4xx, 5xx) this contains the server response
         *                 in case of IO exception this is null
         * @param throwable - contains the exception. in case of server error (4xx, 5xx) this is null
         */
        public void onFailure(Response response, IOException throwable);

        /**
         * contains the server response
         * @param response
         */
        public void onSuccess(Response response);
    }

}
