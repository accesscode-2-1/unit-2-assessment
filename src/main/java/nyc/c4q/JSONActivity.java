package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.json.Zipcode;

public class JSONActivity extends Activity {

    public List<Zipcode> zipcodes;
    public Zipcode zipcode;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        zipcodes = new ArrayList<Zipcode>();
        gson = new Gson();

        final Button savejson = (Button) findViewById(R.id.savejson);
        final Button loadjson = (Button) findViewById(R.id.loadjson);
        final Button addjson = (Button) findViewById(R.id.addjson);

        final TextView _id = (TextView) findViewById(R.id.field_idvalue);
        final TextView pop = (TextView) findViewById(R.id.fieldpopvalue);
        final TextView city = (TextView) findViewById(R.id.fieldcityvalue);
        final TextView state = (TextView) findViewById(R.id.fieldstatevalue);
        final TextView _lat = (TextView) findViewById(R.id.fieldloclatvalue);
        final TextView _long = (TextView) findViewById(R.id.fieldloclongvalue);

        addjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // build zipcode
                String _idS = _id.getText().toString();
                String cityS = city.getText().toString();
                double latS = Double.valueOf(_lat.getText().toString());
                double longS = Double.valueOf(_long.getText().toString());
                double[] location = {latS, longS};
                String stateS = state.getText().toString();
                int popS = Integer.valueOf(pop.getText().toString());

                Zipcode zip =  new Zipcode(_idS, cityS, location, popS, stateS);

                // add it to the list
                zipcodes.add(zip);
            }
        });

        savejson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = getExternalCacheDir();
                File file = new File(directory, "zipcodes.json");
                try {
                    FileOutputStream fout = new FileOutputStream(file);
                    writeJsonStream(fout, zipcodes);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        loadjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJson();
            }
        });
    }

    public void loadJson() {

        File directory = getExternalCacheDir();
        File file = new File(directory, "zipcodes.json");
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = null;
        try {
            jsonArray = (JsonArray) parser.parse(new FileReader(file));
            populateList(jsonArray);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void populateList(JsonArray jsonArray) {
        for (JsonElement zipcode : jsonArray) {
            zipcodes.add(gson.fromJson(zipcode, Zipcode.class));
        }
    }

    public void writeJsonStream(OutputStream out, List<Zipcode> zipcodes) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writeMessagesArray(writer, zipcodes);
        writer.close();
    }

    public void writeMessagesArray(JsonWriter writer, List<Zipcode> zipcodes) throws IOException {
        writer.beginArray();
        for (Zipcode zipcode : zipcodes) {
            writeMessage(writer, zipcode);
        }
        writer.endArray();
    }

    public void writeMessage(JsonWriter writer, Zipcode message) throws IOException {
        writer.beginObject();
        writer.name("_id").value(message.get_id());
        writer.name("city").value(message.getCity());
        writer.name("loc");
        writeDoublesArray(writer, message.getLoc());
        writer.name("pop").value(message.getPop());
        writer.name("state").value(message.getState());
        writer.endObject();
    }


    public void writeDoublesArray(JsonWriter writer, double[] loc) throws IOException {
        writer.beginArray();
        writer.value(loc[0]);
        writer.value(loc[1]);
        writer.endArray();
    }
}
