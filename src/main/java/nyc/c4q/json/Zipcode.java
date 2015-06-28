package nyc.c4q.json;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Zipcode {
    public String _id ="11101";
    public String city = "ASTORIA";
    public String state = "NY";
    public int pop = 23142;

    public double[] loc = new double[]{-73.939393,40.750316};


    public static final String TAG_ID = "_id";
    public static final String TAG_CITY = "city";
    public static final String TAG_STATE = "state";
    public static final String TAG_POPULATION = "pop";
    public static final String TAG_LOCATION = "loc";
    public String data;
    JSONArray jsonArray = null;
    HashMap<String,String> organized = new HashMap<String, String>();


    public Zipcode(String data) {
        this.data = data;
    }

    public String get(String data){
        return this.data;
    }

    public String getField(String tag){
        new GetInformation().execute();
        if(tag == TAG_ID){
            return organized.get("Zipcode");
        }else if(tag == TAG_CITY){
            return organized.get("City");
        }else if(tag == TAG_STATE){
            return organized.get("State");
        }else if( tag == TAG_POPULATION){
            return organized.get("Population");
        }else{
            return null;
        }
    }



    private class GetInformation extends AsyncTask<Void ,Void, Void>{

        @Override
        protected Void doInBackground(Void... params){

            String jsonStr = data;


            if(jsonStr != null){
                try{
                    jsonArray = new JSONArray(jsonStr);

                    for(int i=0; i < jsonArray.length();i++ ){
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString(TAG_ID);
                        String city = object.getString(TAG_CITY);
                        String state = object.getString(TAG_STATE);
                        String population = object.getString(TAG_POPULATION);
                        String location = object.getString(TAG_LOCATION);

                        organized.put("Zipcode",id);
                        organized.put("City",city);
                        organized.put("State", state);
                        organized.put("Population", population);



                    }


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

    }

}
