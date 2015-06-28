package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
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

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class NetworkActivity extends Activity {

    // Fields ===========================

    public TextView httptextlog;
    public Button httpbinget;
    public Button httpbingetokhttp;
    public Button httpbinpost;
    public Button httpbinpostokhttp;
    public Button cleartextlog;
    public String input;
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

        /*
        The goal is to use AsyncTasks here.
        Shortcut to create URL in Java:

            String.format("https://httpbin.org/get?%s", urlParams);

        HTTP GET request we'll be using:

            $ curl "https://httpbin.org/get?custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don%27t+ask+any+questions."

        HTTP POST request we'll be using:

            $ curl -X POST \
                -d "custname=james dean" \
                -d "custtel=347-8431-6090" \
                -d "custemail=hello@c4q.nyc" \
                -d "size=small" \
                -d "topping=cheese" \
                -d "delivery=22:15" \
                -d "comments=Leave it by the garage door. Don't ask any questions." \
                https://httpbin.org/post
        */

        httpbinget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httptextlog.setText(String.format("https://httpbin.org/get?%s", urlParams));
            }
        });

        httpbingetokhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String replaced = urlParams.replaceAll("\\+"," ");
//                httptextlog.setText(replaced);
                AsyncTask get = new NetworkAsync();
            }
        });

        httpbinpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        httpbinpostokhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        cleartextlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httptextlog.setText("cleared HTTP response");
            }
        });
    }

    public class NetworkAsync extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... empty) {
            input = readURL();
            return null;
        }

        protected void onPostExecute(Void result) {
            httptextlog.setText(input);
        }

        public String readURL() {
            String url = String.format("https://httpbin.org/get?%s", urlParams);

            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                org.apache.http.HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }
    }
}
