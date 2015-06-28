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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    String result;

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
                result = "\"_id\":\"" + _id.getText() + "\"" + "\"pop\":" + pop.getText() +
                        "\"city\":\"" + city.getText() + "\"" + "\"state\":\"" + state.getText() + "\"" +
                        "\"loc\":[" + _lat.getText() + "," + _long.getText() + "]";
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                FileOutputStream outputStream;
                try {
                    outputStream = new FileOutputStream(file);
                    outputStream.write(result.getBytes());
                    outputStream.close();
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
                String json = "";

                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader bReader = new BufferedReader(fReader);
                    StringBuilder text = new StringBuilder();

                    while ((json = bReader.readLine()) != null) {
                        text.append(json + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONObject reader = null;
                try {
                    reader = new JSONObject(json);
                    String parseID = reader.getString("_id");
                    String parsePOP = reader.getString("pop");
                    String parseCITY = reader.getString("city");
                    String parseSTATE = reader.getString("state");
                    String parseLOC = reader.getString("loc");
                    parseLOC = parseLOC.substring(1, parseLOC.length()-1);
                    String parseLAT = parseLOC.split(",");
                    String parseLONG = parseLOC.substring(12, 21);

                    _id.setText(parseID);
                    pop.setText(parsePOP);
                    city.setText(parseCITY);
                    state.setText(parseSTATE);
                    _lat.setText(parseLAT);
                    _long.setText(parseLONG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}