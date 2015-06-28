package nyc.c4q.json;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Zipcode {

    String _id = parsed().get(0);
    String city = parsed().get(1);
    String state = parsed().get(2);
    int pop = pop();
    double _lat = loc().get(0);
    double _long = loc().get(1);


    public ArrayList<String> parsed() {
        ArrayList<String> zipInfo = new ArrayList<String>();
        JsonParser jsonParser = new JsonParser();

        try {
            Object obj = jsonParser.parse(new FileReader("/Users/c4q-anthony-mcbride/Desktop/Exercises/unit-2-assessment/src/main/res/raw/zips.json"));

            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;


            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.get(i).toString().contains("11101")) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    zipInfo.add(jsonObject.get("_id").toString());
                    zipInfo.add(jsonObject.get("city").toString());
                    zipInfo.add(jsonObject.get("state").toString());
                    return zipInfo;
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return zipInfo;
    }

    public int pop() {
        int pop = 0;

        JsonParser jsonParser = new JsonParser();

        try {
            Object obj = jsonParser.parse(new FileReader("/Users/c4q-anthony-mcbride/Desktop/Exercises/unit-2-assessment/src/main/res/raw/zips.json"));

            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;


            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.get(i).toString().contains("11101")) {
                    JSONObject population = (JSONObject) jsonArray.get(i);
                    pop = population.getInt("pop");
                    return pop;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return pop;
    }
    public ArrayList<Double> loc(){
        ArrayList<Double> zipInfo = new ArrayList<Double>();
        JsonParser jsonParser = new JsonParser();

        try {
            Object obj = jsonParser.parse(new FileReader("/Users/c4q-anthony-mcbride/Desktop/Exercises/unit-2-assessment/src/main/res/raw/zips.json"));

            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;


            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.get(i).toString().contains("11101")) {
                    JSONArray locs = (JSONArray) jsonArray.get(i);
                    JSONObject lat = (JSONObject) locs.get(0);
                    JSONObject lon = (JSONObject) locs.get(1);

                    zipInfo.add(locs.getDouble(0));
                    zipInfo.add(locs.getDouble(1));
                    return zipInfo;
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return zipInfo;
    }
}
