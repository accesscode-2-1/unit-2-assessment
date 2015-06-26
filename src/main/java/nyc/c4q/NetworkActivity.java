package nyc.c4q;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NetworkActivity extends Activity {


    // Helpers ===========================

    public class HTTPGETTask extends AsyncTask<String, Integer, ArrayList<String>> {
        protected ArrayList<String> doInBackground(String... urls) {
            ArrayList<String> results = new ArrayList<String>();
            // TODO insert code here
            return results;
        }

        protected void onPostExecute(ArrayList<String> results) {
            // TODO insert code here
        }
    }

    public class OKHTTPGETTask extends AsyncTask<String, Integer, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... urls) {
            ArrayList<String> results = new ArrayList<String>();
            OkHttpClient client = new OkHttpClient();
            // TODO insert code here
            return results;
        }

        protected void onPostExecute(ArrayList<String> results) {
            // TODO insert code here
        }
    }

    public class HTTPPOSTTask extends AsyncTask<URLAndURLParams, Integer, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(URLAndURLParams... urls) {
            ArrayList<String> results = new ArrayList<String>();
            // TODO insert code here
            return results;
        }

        protected void onPostExecute(ArrayList<String> results) {
            // TODO insert code here
        }
    }

    public class OKHTTPPOSTTask extends AsyncTask<URLAndURLParams, Integer, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(URLAndURLParams... urls) {
            ArrayList<String> results = new ArrayList<String>();
            OkHttpClient client = new OkHttpClient();
            // TODO insert code here
            return results;
        }

        protected void onPostExecute(ArrayList<String> results) {
            // TODO insert code here
        }
    }

    public static class URLAndURLParams {
        public String url;
        public String urlParams;

        public URLAndURLParams(String url, String urlParams) {
            this.url = url;
            this.urlParams = urlParams;
        }
    }

    //http://stackoverflow.com/a/5445161/198348
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    // Fields ===========================

    public TextView httptextlog;
    public Button httpbinget;
    public Button httpbingetokhttp;
    public Button httpbinpost;
    public Button httpbinpostokhttp;
    public Button cleartextlog;
    final public String urlParams = "custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don't+ask+any+questions.";

    // Code ===========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        httpbinget = (Button) findViewById(R.id.httpbinget);
        httpbingetokhttp = (Button) findViewById(R.id.httpbingetokhttp);
        httpbinpost = (Button) findViewById(R.id.httpbinpost);
        httpbinpostokhttp = (Button) findViewById(R.id.httpbinpostokhttp);
        cleartextlog = (Button) findViewById(R.id.cleartextlog);
        httptextlog = (TextView) findViewById(R.id.httptextlog);
        httptextlog.setMovementMethod(new ScrollingMovementMethod());

        httpbinget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HTTPGETTask().execute("https://httpbin.org/get" + "?" + urlParams);
            }
        });

        httpbingetokhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OKHTTPGETTask().execute("https://httpbin.org/get" + "?" + urlParams);
            }
        });

        httpbinpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HTTPPOSTTask().execute(new URLAndURLParams("https://httpbin.org/post", urlParams));
            }
        });

        httpbinpostokhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OKHTTPPOSTTask().execute(new URLAndURLParams("https://httpbin.org/post", urlParams));
            }
        });

        cleartextlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httptextlog.setText("cleared HTTP response");
            }
        });
    }
}
