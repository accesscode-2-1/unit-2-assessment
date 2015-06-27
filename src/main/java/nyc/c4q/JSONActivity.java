package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

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
                Zipcode z = new Zipcode();
                Log.d(Unit2AssessmentActivity.TAG, _id.getText().toString());
                z._id = _id.getText().toString();
                z.pop = Integer.parseInt(pop.getText().toString());
                z.city = city.getText().toString();
                z.state = state.getText().toString();
                z._lat = Double.parseDouble(_lat.getText().toString());
                z._long = Double.parseDouble(_long.getText().toString());
                z.loc = new double[]{z._lat,z._long};
                Log.d(Unit2AssessmentActivity.TAG, z.toString());

                zipcodes.add(z);
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String result = gson.toJson(zipcodes, Zipcode[].class);
            }
        });

    }
}
