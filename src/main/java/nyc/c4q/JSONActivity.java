package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.JsonWriter;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    String json;

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
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("_id", _id.getText().toString());
                    jsonObj.put("pop", pop.getText().toString());
                    jsonObj.put("city", city.getText().toString());
                    jsonObj.put("state", state.getText().toString());
                    jsonObj.put("loc", "[" + _lat.getText() + "," + _long.getText() + "]");

                    json = jsonObj.toString();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
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
                    outputStream.write(json.getBytes());
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
                String readfile = "";
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String s;
                    while ((s = br.readLine()) != null) {
                        readfile += s;
                    }
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONArray reader = null;
                try {
                    reader = new JSONArray(readfile);
                    JSONObject one = reader.getJSONObject(0);
                    String parseID = one.getString("_id");
                    String parsePOP = one.getString("pop");
                    String parseCITY = one.getString("city");
                    String parseSTATE = one.getString("state");
                    String parseLOC = one.getString("loc");
                    parseLOC = parseLOC.substring(1, parseLOC.length() - 1);
                    List<String> locArray = Arrays.asList(parseLOC.split(","));

                    _id.setText(parseID);
                    pop.setText(parsePOP);
                    city.setText(parseCITY);
                    state.setText(parseSTATE);
                    _lat.setText(locArray.get(0));
                    _long.setText(locArray.get(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}