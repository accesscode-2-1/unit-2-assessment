package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    public static final String JSON_ZIPCODE = "{\"_id\":\"11101\",\"city\":\"ASTORIA\",\"loc\":[-73.939393,40.750316],\"pop\":23142,\"state\":\"NY\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        zipcodes = new ArrayList<Zipcode>();

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
                try {
                    JSONObject jsonObject= new JSONObject(JSON_ZIPCODE);
                    _id.setText(jsonObject.get("_id").toString());
                    pop.setText(jsonObject.get("pop").toString());
                    city.setText(jsonObject.get("city").toString());
                    state.setText(jsonObject.get("state").toString());
                    _lat.setText(jsonObject.get("_lat").toString());
                    _long.setText(jsonObject.get("_lat").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");

                FileOutputStream fileOutputStream = null;
                try{
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(JSON_ZIPCODE.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        loadjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                FileInputStream fis = null;
                try{
                    fis = new FileInputStream(file);
                    int read = -1;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((read = fis.read())!=-1){
                        stringBuffer.append((char)read);
                    }
                    String result = stringBuffer.toString();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public class ZipCode{

        public String _id;
        public String pop;
        public String city;
        public String state;
        public String _lat;
        public String _long;

        public void intializeStrings() throws JSONException {
            _id = getField("_id");
            pop = getField("pop");
            city = getField("city");
            state = getField("state");
            _lat = getField("_lat");
            _long = getField("_long");
        }
        public String getField(String params) throws JSONException {
            JSONObject jsonObject= new JSONObject(JSON_ZIPCODE);
            return jsonObject.get(params).toString();
        }
    }
}
