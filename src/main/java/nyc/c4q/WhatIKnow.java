package nyc.c4q;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by c4q-joshelynvivas on 6/28/15.\
 *
 * TODO: What I do know about the JSON Parsing. Brackets are Arrays and Curly Brackets are Objects. The example is from the weather Parse
 *
 */
public class WhatIKnow {

        // http://api.openweathermap.org/data/2.5/weather?zip=11367,us -- URL
    //Endpoint is either the http://link or the beginning part of it (ex: zip=)
    //Suffix is the ending part of the website once the person enters something in

    private static final String JSON_URL_ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?zip=";
        private static final String JSON_URL_SUFFIX = ",us";

        private static String jsonString = "";

        // Goal 1
        private static String getJsonString() {
            String result = "";

            // TODO : Step 0 - create a completed json url string by adding your own zip code
            String jsonUrl = JSON_URL_ENDPOINT + "11367" + JSON_URL_SUFFIX;


            // TODO : Step 1 - create an URL instance with Step 0's result
            try {
                //converting the json Url to an actual URL
                URL url = new URL(jsonUrl);

                // TODO : Step 2 - create a Http Url Connection with Step 1's URL
                //make sure to have internet permission before doing this
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(0);
                connection.setReadTimeout(0);

                // TODO : Step 3 - get inputstream from Step 2 and create an instance of BufferedReader
               //I believe this is grabbing the information to go to the project
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // TODO : Step 4 - create a string builder instance
                //going to create Strings
                StringBuilder stringBuilder = new StringBuilder();

                // TODO : Step 5 - read json file until the end of the line and write into string builder and save it into String variable "result"
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        // Goal 2- getting Humidity of the weather api
        public static int getHumidity() {
            int humidity = -1;

            jsonString = getJsonString();
            try {
                //since the file has the object first, we need the JSON to see the Object, then go down the families to get what you want
                JSONObject object = new JSONObject(jsonString);
                JSONObject main = object.getJSONObject("main");
                humidity = main.getInt("humidity");
            } catch (JSONException e) {        //catch helps prevent errors
                e.printStackTrace();
            }

            return humidity;
        }
    }
