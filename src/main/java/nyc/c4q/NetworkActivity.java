package nyc.c4q;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkActivity extends Activity {

    // Fields ===========================

    public TextView httptextlog;
    public Button httpbinget;
    public Button httpbingetokhttp;
    public Button httpbinpost;
    public Button httpbinpostokhttp;
    public Button cleartextlog;
    final public String urlParams = "custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don't+ask+any+questions.";
    public AsyncTask asyncHttps;

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
                if(asyncHttps == null){
                    asyncHttps = new AsyncHttps().execute();
                }else if(!asyncHttps.isCancelled()){
                    asyncHttps.cancel(true);
                    asyncHttps = new AsyncHttps().execute();
                }
            }
        });

        httpbingetokhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asyncHttps == null){
                    asyncHttps = new AsyncOkHttps().execute();
                }else if(!asyncHttps.isCancelled()){
                    asyncHttps.cancel(true);
                    asyncHttps = new AsyncOkHttps().execute();
                }
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

    public class AsyncOkHttps extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://httpbin.org/get?"+urlParams)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null){
                httptextlog.setText(s);
            }
        }
    }

    public class AsyncHttps extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            String httpbin = null;

            try {
                URL url = new URL("https://httpbin.org/get?" + urlParams);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = httpsURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                httpbin = builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return httpbin;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            httptextlog.setText(s);
        }
    }
}
