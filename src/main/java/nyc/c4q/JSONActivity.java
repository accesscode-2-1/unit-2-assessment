package nyc.c4q;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
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
        final TextView jsonViewer = (TextView) findViewById(R.id.json_viewer);

        addjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !(_id.getText().toString().equals("")
                        || pop.getText().toString().equals("")
                        || city.getText().toString().equals("")
                        || state.getText().toString().equals("")
                        || _lat.getText().toString().equals("")
                        || _long.getText().toString().equals("")) ) {

                    Zipcode zipcode = new Zipcode();

                    zipcode.id = _id.getText().toString();
                    zipcode.pop = pop.getText().toString();
                    zipcode.city = city.getText().toString();
                    zipcode.state = state.getText().toString();
                    String[] loc = {_lat.getText().toString(),_long.getText().toString()};
                    zipcode.loc = loc;

                    _id.setText("");
                    pop.setText("");
                    city.setText("");
                    state.setText("");
                    _lat.setText("");
                    _long.setText("");

                    zipcodes.add(zipcode);
                    (Toast.makeText(getApplicationContext(),"Added to Zipcodes",Toast.LENGTH_LONG)).show();

                }else{
                    (Toast.makeText(getApplicationContext(),"Fill in all fields",Toast.LENGTH_LONG)).show();
                }
                generateJSON();
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    ObjectOutputStream os = new ObjectOutputStream(fos);
                    os.writeObject(json);
                    os.close();
                    fos.close();
                    (Toast.makeText(getApplicationContext(),"Zipcodes Saved",Toast.LENGTH_LONG)).show();
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
                    ObjectInputStream is = new ObjectInputStream(fis);
                    json = (String) is.readObject();
                    is.close();
                    fis.close();
                    (Toast.makeText(getApplicationContext(),"Zipcodes Loaded",Toast.LENGTH_LONG)).show();
                    jsonViewer.setText(json);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                } catch (StreamCorruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void generateJSON(){
        json = new Gson().toJson(zipcodes);
    }

}
