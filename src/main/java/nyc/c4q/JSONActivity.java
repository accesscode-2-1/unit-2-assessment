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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(List<Zipcode> zipcodes) {
        this.zipcodes = zipcodes;
    }

    public TextView get_long() {
        return _long;
    }

    public void set_long(TextView _long) {
        this._long = _long;
    }

    public TextView get_lat() {
        return _lat;
    }

    public void set_lat(TextView _lat) {
        this._lat = _lat;
    }

    public TextView getCity() {
        return city;
    }

    public void setCity(TextView city) {
        this.city = city;
    }

    public TextView getPop() {
        return pop;
    }

    public void setPop(TextView pop) {
        this.pop = pop;
    }

    public TextView get_id() {
        return _id;
    }

    public void set_id(TextView _id) {
        this._id = _id;
    }

    public TextView getState() {
        return state;
    }

    public void setState(TextView state) {
        this.state = state;
    }

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
        Button loadjson = (Button) findViewById(R.id.loadjson);
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
