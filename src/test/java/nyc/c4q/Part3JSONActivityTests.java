package nyc.c4q;

import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowEnvironment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import nyc.c4q.json.Zipcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Part3JSONActivityTests {

    private JSONActivity jsonActivity;
    // C4Q's Zipcode.
    public static final String JSON_ZIPCODE = "{\"_id\":\"11101\",\"city\":\"ASTORIA\",\"loc\":[-73.939393,40.750316],\"pop\":23142,\"state\":\"NY\"}";

    @Before
    public void setUp() {
        jsonActivity = Robolectric.buildActivity(JSONActivity.class).setup().get();
    }

    @Test
    public void test16JSONActivityCreateJSONMappingID() throws NoSuchFieldException, IllegalAccessException {
        Gson gson = new Gson();
        Zipcode z = gson.fromJson(JSON_ZIPCODE, Zipcode.class);

        assertThat(Zipcode.class.getField("_id").get(z), instanceOf(String.class));
        assertThat((String) Zipcode.class.getField("_id").get(z), equalTo("11101"));

        assertThat(Zipcode.class.getField("city").get(z), instanceOf(String.class));
        assertThat((String) Zipcode.class.getField("city").get(z), equalTo("ASTORIA"));

        assertThat(Zipcode.class.getField("state").get(z), instanceOf(String.class));
        assertThat((String) Zipcode.class.getField("state").get(z), equalTo("NY"));

        assertThat(Zipcode.class.getField("pop").get(z), instanceOf(int.class));
        assertThat((Integer) Zipcode.class.getField("pop").get(z), equalTo(23142));
    }

    @Test
    public void test17JSONActivityCreateJSONMappingLoc() throws NoSuchFieldException, IllegalAccessException {
        Gson gson = new Gson();
        Zipcode z = gson.fromJson(JSON_ZIPCODE, Zipcode.class);

        assertThat(Zipcode.class.getField("loc").get(z), instanceOf(double[].class));
        assertThat(((double[]) Zipcode.class.getField("loc").get(z))[0], closeTo(-73.939393, 0.01));
        assertThat(((double[]) Zipcode.class.getField("loc").get(z))[1], closeTo(40.750316, 0.01));
    }

    @Test
    public void test18JSONActivityCheckAddJSONButton() {
        Gson gson = new Gson();
        TextView _id = (TextView) jsonActivity.findViewById(R.id.field_idvalue);
        TextView pop = (TextView) jsonActivity.findViewById(R.id.fieldpopvalue);
        TextView city = (TextView) jsonActivity.findViewById(R.id.fieldcityvalue);
        TextView state = (TextView) jsonActivity.findViewById(R.id.fieldstatevalue);
        TextView _lat = (TextView) jsonActivity.findViewById(R.id.fieldloclatvalue);
        TextView _long = (TextView) jsonActivity.findViewById(R.id.fieldloclongvalue);
        Button addjson = (Button) jsonActivity.findViewById(R.id.addjson);

        _id.setText("11101");
        pop.setText("23142");
        city.setText("ASTORIA");
        state.setText("NY");
        _lat.setText("-73.939393");
        _long.setText("40.750316");

        addjson.callOnClick();

        //TODO un-hack
        String result = gson.toJson(jsonActivity.zipcodes.get(0), Zipcode.class);
        assertThat(result, containsString("\"_id\":\"11101\""));
        assertThat(result, containsString("\"pop\":23142"));
        assertThat(result, containsString("\"city\":\"ASTORIA\""));
        assertThat(result, containsString("\"state\":\"NY\""));
        assertThat(result, containsString("\"loc\":[-73.939393,40.750316]"));
    }

    @Test
    public void test19JSONActivityCheckSaveJSONButton() throws FileNotFoundException {
        ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);

        TextView _id = (TextView) jsonActivity.findViewById(R.id.field_idvalue);
        TextView pop = (TextView) jsonActivity.findViewById(R.id.fieldpopvalue);
        TextView city = (TextView) jsonActivity.findViewById(R.id.fieldcityvalue);
        TextView state = (TextView) jsonActivity.findViewById(R.id.fieldstatevalue);
        TextView _lat = (TextView) jsonActivity.findViewById(R.id.fieldloclatvalue);
        TextView _long = (TextView) jsonActivity.findViewById(R.id.fieldloclongvalue);
        Button addjson = (Button) jsonActivity.findViewById(R.id.addjson);
        Button savejson = (Button) jsonActivity.findViewById(R.id.savejson);

        _id.setText("11101");
        pop.setText("23142");
        city.setText("ASTORIA");
        state.setText("NY");
        _lat.setText("-73.939393");
        _long.setText("40.750316");

        addjson.callOnClick();
        savejson.callOnClick();
        File directory = jsonActivity.getExternalCacheDir();
        File file = new File(directory, "zipcodes.json");
        String results = new Scanner(file).useDelimiter("\\Z").next();

        // TODO un-hack
        assertThat(results, containsString("\"_id\":\"11101\""));
        assertThat(results, containsString("\"pop\":23142"));
        assertThat(results, containsString("\"city\":\"ASTORIA\""));
        assertThat(results, containsString("\"state\":\"NY\""));
        assertThat(results, containsString("\"loc\":[-73.939393,40.750316]"));
    }

    @Test
    public void test20JSONActivityCheckLoadJSONButton() throws IOException {
        ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);
        Gson gson = new Gson();
        Button loadjson = (Button) jsonActivity.findViewById(R.id.loadjson);

        File directory = jsonActivity.getExternalCacheDir();
        File file = new File(directory, "zipcodes.json");

        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(String.format("[%s]",JSON_ZIPCODE));
        fileWriter.close();

        loadjson.callOnClick();

        String result = gson.toJson(jsonActivity.zipcodes.get(0), Zipcode.class);

        assertThat(result, containsString("\"_id\":\"11101\""));
        assertThat(result, containsString("\"pop\":23142"));
        assertThat(result, containsString("\"city\":\"ASTORIA\""));
        assertThat(result, containsString("\"state\":\"NY\""));
        assertThat(result, containsString("\"loc\":[-73.939393,40.750316]"));
    }

}
