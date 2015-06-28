package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.stream.JsonWriter;


import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    private JSONArray jsonArray;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        path =  getExternalCacheDir().toString();

        zipcodes = new ArrayList<Zipcode>();

        Zipcode x = new Zipcode(11101);
//        x.setCity("ASTORIA");
//        x.setLoc("-73.939393,40.750316");
//        x.setPop(23142);
        Zipcode y = new Zipcode(10010);
        Zipcode z = new Zipcode(11121);
        zipcodes.add(x);
        zipcodes.add(y);
        zipcodes.add(z);
        Button savejson = (Button) findViewById(R.id.savejson);
        final Button loadjson = (Button) findViewById(R.id.loadjson);
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
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                //fixme


            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                JSONArray array = new JSONArray();

                for (Zipcode c : zipcodes) {
                    array.put(c);
                }
                Writer writer = null;
                try {
                    OutputStream out = new ObjectOutputStream(new FileOutputStream(path + "zipcodes.json"));
                    JsonWriter jsonWriter = new JsonWriter(writer);
                    writer = new OutputStreamWriter(out);
                    writer.write(array.toString());
                    jsonWriter.name("zipcodes").value("unittest");
                    jsonWriter.beginArray();
                    jsonWriter.value(zipcodes.get(0).toString());
                    jsonWriter.value(zipcodes.get(1).toString());
                    jsonWriter.value(zipcodes.get(2).toString());
                    jsonWriter.endArray();
                } catch(Exception e){

                }
                finally {
                    if (writer != null)
                        try {
                            writer.close();

                        } catch(Exception e){

                        }

                }

            }
        });


        loadjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
            }
        });




    }




}
