package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    Zipcode z;

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
                String Sid = _id.getText().toString();
                String Scity = city.getText().toString();
                String Sstate = state.getText().toString();
                int Spop = Integer.valueOf(pop.getText().toString());
                double[] Sloc = {Double.valueOf(_lat.getText().toString()), Double.valueOf(_long.getText().toString())};

                z = new Zipcode(Sid, Scity, Sstate, Spop, Sloc);
                zipcodes.add(z);
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
                    outputStream.write(z.toString().getBytes());
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
                    JSONObject obj = reader.getJSONObject(0);
                    String parseID = obj.getString("_id");
                    int parsePOP = Integer.valueOf(obj.getString("pop"));
                    String parseCITY = obj.getString("city");
                    String parseSTATE = obj.getString("state");
                    String parseLOC = obj.getString("loc");
                    parseLOC = parseLOC.substring(1, parseLOC.length() - 1);
                    List<String> locArray = Arrays.asList(parseLOC.split(","));
                    double[] pl = {Double.valueOf(locArray.get(0)), Double.valueOf(locArray.get(1))};

                    Zipcode a = new Zipcode(parseID, parseCITY, parseSTATE, parsePOP, pl);
                    zipcodes.add(a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}