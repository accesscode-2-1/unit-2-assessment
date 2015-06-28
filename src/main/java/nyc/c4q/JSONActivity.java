package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {
    public List<Zipcode> zipcodes;
    public TextView _id;
    public TextView pop;
    public TextView city;
    public TextView state;
    public TextView _lat;
    public TextView _long;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        zipcodes = new ArrayList<Zipcode>();

        Button savejson = (Button) findViewById(R.id.savejson);
        final Button loadjson = (Button) findViewById(R.id.loadjson);
        Button addjson = (Button) findViewById(R.id.addjson);

        _id = (TextView) findViewById(R.id.field_idvalue);
        pop = (TextView) findViewById(R.id.fieldpopvalue);
        city = (TextView) findViewById(R.id.fieldcityvalue);
        state = (TextView) findViewById(R.id.fieldstatevalue);
         _lat = (TextView) findViewById(R.id.fieldloclatvalue);
        _long = (TextView) findViewById(R.id.fieldloclongvalue);

        addjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();

            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            File directory = getExternalCacheDir();
                                            File file = new File(directory, "zipcodes.json");
                                            FileWriter fileWriter = null;
                                            try {
                                                fileWriter = new FileWriter(file, false);
                                                fileWriter.write("id:"+_id.getText().toString());
                                                fileWriter.write("pop"+pop.getText().toString());
                                                fileWriter.write("city"+city.getText().toString());
                                                fileWriter.write("state:"+state.getText().toString());
                                                fileWriter.write("loc:"+_lat.getText().toString()+","+_long.getText().toString());
                                                fileWriter.close();

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            loadjson.callOnClick();


                                            try {
                                                FileInputStream fis=openFileInput("zipcodes.json");
                                                StringBuffer buffer= new StringBuffer();
                                                int read=-1;
                                                while((read=fis.read())!=-1){
                                                    buffer.append((char)read);
                                                }
                                                String output=buffer.toString();


                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            } finally {

                                            }


                                        }
                                    });



        loadjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                JsonParser parser = new JsonParser();
                try {
                    Object object = parser.parse(new FileReader(file));
                    JSONArray jsonObject = (JSONArray) object;

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject object1 = (JSONObject) jsonObject.get(i);
                        String id = object1.getString("_id");
                        _id.setText(id);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
