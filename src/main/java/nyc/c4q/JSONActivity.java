package nyc.c4q;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;

    Zipcode zp = new Zipcode();
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        zipcodes = new ArrayList<Zipcode>();
        result = String.valueOf(zipcodes.get(0));

        Button savejson = (Button) findViewById(R.id.savejson);
        Button loadjson = (Button) findViewById(R.id.loadjson);
        Button addjson = (Button) findViewById(R.id.addjson);

        final TextView _id = (TextView) findViewById(R.id.field_idvalue);
        final TextView pop = (TextView) findViewById(R.id.fieldpopvalue);
        final TextView city = (TextView) findViewById(R.id.fieldcityvalue);
        final TextView state = (TextView) findViewById(R.id.fieldstatevalue);
        final TextView _lat = (TextView) findViewById(R.id.fieldloclatvalue);
        final TextView _long = (TextView) findViewById(R.id.fieldloclongvalue);

        addjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Read the entire file using getEntireJSON method
               // getJSON();
               //Parse the file to obtain id, pop, city, etc... using parseJSON method
               // parseJSON();
                try {
                    JSONObject zipcode = new JSONObject(result);
                    zp.setId(zipcode.getString("_id"));
                    zp.setCity(zipcode.getString("city"));
                    zp.setPop(zipcode.getInt("pop"));
                    zp.setState(zipcode.getString("state"));
                    zp.setLat_(zipcode.getString("-73.939393"));
                    zp.setLong_(zipcode.getString("40.750316"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                _id.setText(zp.getId());
                pop.setText(zp.getPop()+"");
                city.setText(zp.getCity());
                state.setText(zp.getState());
                _lat.setText(zp.getLat_());
                _long.setText(zp.getLong_());


            }
        });
//Save to file http://stackoverflow.com/questions/19315316/saving-json-file-from-url-in-internal-storage
        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput("zipcodes.json", Context.MODE_PRIVATE);
                    outputStream.write(result.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

//open file from gallery/documents
        loadjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                try {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(openFileInput("zipcodes.json")));
                    String line;
                    StringBuffer text = new StringBuffer();
                    while ((line = bReader.readLine()) != null) {
                        text.append(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private String getEntireJSON(String urlParams) {
        String result= "";
        URL url = null;
        try {
            url = new URL(urlParams);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(0);
            connection.setReadTimeout(0);

            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while((line = reader.readLine()) !=null){
                sb.append(line + "\n");
            }
            result = sb.toString();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



}
