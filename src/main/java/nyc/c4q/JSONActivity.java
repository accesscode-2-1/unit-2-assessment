package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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

        addjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zipcode zip = new Zipcode();
                zip.set_id(_id.getText().toString());
                zip.setCity(city.getText().toString());
                double latitude = Double.parseDouble(_lat.getText().toString());
                double longitude = Double.parseDouble(_long.getText().toString());
                zip.setLoc(new double[]{latitude, longitude});
                zip.setPop(Integer.parseInt(pop.getText().toString()));
                zip.setState(state.getText().toString());
                zipcodes.add(zip);
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");

                try{
                    FileOutputStream fout = new FileOutputStream(file);

                    for(Zipcode zipcode : zipcodes) {

                       String full = zipcode.toString();
                        fout.write(full.getBytes());
                        fout.close();
                    }

                }catch(Exception e){
                    e.printStackTrace();
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
