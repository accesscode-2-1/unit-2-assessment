package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    public boolean storageReadable;
    public boolean storageWriteable;
    public boolean storageAvailable;
    public String results = "";
    public final String LOG_TAG = "";

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

        storageReadable = isExternalStorageReadable();
        storageWriteable = isExternalStorageWritable();

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

                results += "\"_id\":" + _id.getText().toString();
                results += "\"pop\":" + pop.getText().toString();
                results += "\"city\":" + city.getText().toString();
                results += "\"state\":" + state.getText().toString();
                results += "\"loc\":" + _lat.getText().toString() + "," + _long.getText().toString();

                Log.d(LOG_TAG, results);

                /* It's hard for me to understand what this test wants. */


                if (storageWriteable) {
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(results.getBytes());
                        fos.flush();
                        fos.close();
                        Toast.makeText(JSONActivity.this, "File saved to: " +
                                directory + file, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
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

    // Can we read/write on external storage?
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    // Can we at least read external storage?
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
