package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
                Gson gson = new Gson();
                Zipcode z = gson.fromJson(JSON_ZIPCODE, Zipcode.class);
                zipcodes.add(z);
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                FileOutputStream fout=null;
                ObjectOutputStream oos=null;
                try {
                    fout = new FileOutputStream(file);
                    PrintWriter pw = new PrintWriter(zipcodes.get(0).toString());
                    pw.flush();
                    pw.close();
                    fout.close();
                }catch(Exception e){

                }finally {
                    if(fout!=null){
                        try {
                            fout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(oos!=null){
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
