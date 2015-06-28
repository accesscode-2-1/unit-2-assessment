package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NetworkActivity extends Activity {

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
            }
        });

        httpbingetokhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    /*
    private class getUrlParm extends AsyncTask<String, Void, String>{
        String myUrlLink;

        protected String doInBackground(String... urls){
            try{

            }catch(IOException e){

            }
        }
    }
    */

}

/**
 * private class DownloadData extends AsyncTask<String, Void,String> {

 String myXmlData;

 protected String doInBackground(String... urls) { //the ... means it will accept 0 or many urls by deinition with this AsyncTask we can proess more than one thing
 try {
 myXmlData = downloadXML(urls[0]);

 } catch (IOException e) {
 return "Unable to download XML file";
 }

 return "";
 }

 protected void onPostExecute(String result){
 Log.d("OnPostExecute", myXmlData);
 xmlData = myXmlData;

 }

 private String downloadXML(String theUrl) throws IOException { //if there is an error in this method, thorow it back to the calling method and we want that method to deal with it
 int BUFFER_SIZE = 2000;   //20000 chara at a time.
 InputStream is = null;  //the mecanism we will use to do the download

 String xmlContents = ""; //temp container for our data

 try {
 URL url = new URL(theUrl);  //start opening the url the website address
 HttpURLConnection conn = (HttpURLConnection) url.openConnection();  //open a link or a a reference to that website
 conn.setReadTimeout(10000);  //for whatever the reason we cannot download the file we want to close it gracefully. the maximum time to wait for an input stream read before giving up mill sec
 conn.setConnectTimeout(15000); //connection timer same reason as before
 conn.setRequestMethod("GET");  // get is a stanard way to access data for a web browser
 conn.setDoInput(true);          //input data
 int response = conn.getResponseCode();     //get a response and see what happens
 Log.d("DownloadXML", "The response returned is: " + response); //if the response was okay then it would be 200
 is = conn.getInputStream();

 InputStreamReader isr = new InputStreamReader(is);  //read through whatever we send you
 int charRead;
 char[] inputBuffer = new char[BUFFER_SIZE];
 try {
 while ((charRead = isr.read(inputBuffer)) > 0)
 {   //Reading through the inputBUffer charRead is the number of characters that have been read by this process
 String readString = String.copyValueOf(inputBuffer, 0, charRead);//go through the array and we start at 0 and go through however many character have been read

 xmlContents += readString; //continually add whatever we read to the xmlContents string
 // and then now that we've read that we want to clear out the inputBuffer
 inputBuffer = new char[BUFFER_SIZE];
 }

 return xmlContents;

 } catch (IOException e) {
 e.printStackTrace();  //shows where it crashed
 return null;          // if there was a problem then it wont return anything
 }

 } finally {
 if (is != null)  //no matter what, if there's an error we still want to execute the code that's in here
 is.close();   //whether there is an error or not, make sure we close this InputStream
 }
 }
 }
 *
 */