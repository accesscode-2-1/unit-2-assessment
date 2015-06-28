package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
                z = new Zipcode("11101", "ASTORIA", "NY", 23142, new double[] {-73.939393,40.750316});
                zipcodes.add(z);


            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "\"_id\":\"" + z.get_id() + "\"pop\":\"" + "" + z.getPop() + "\"city\":\"" + z.getCity() + "\"state\":\"" + z.getState()  + "" + "\"loc\":\"" + "" + z.getLoc()[0] + "" + "\"loc\":\"" + "" + z.getLoc()[1] + "";
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileWriter.write(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileWriter.close();
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
                String result= "";

                try {
                    FileInputStream fis = new FileInputStream("zipcodes.json");
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        result +=strLine;
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
    }
}
