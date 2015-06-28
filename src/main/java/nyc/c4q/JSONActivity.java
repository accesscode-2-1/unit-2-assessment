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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;

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

        JSONArray address = new JSONArray(zipcodes);
            for (int i = 0; i < address.length(); i++) {
                try {
                    JSONObject info = address.getJSONObject(i);
                    Zipcode data = new Zipcode();
                    data.set_id(info.getString("_id"));
                    data.setCity(info.getString("city"));
                    data.setState(info.getString("state"));
                    data.setPop(info.getInt("pop"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        addjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
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
