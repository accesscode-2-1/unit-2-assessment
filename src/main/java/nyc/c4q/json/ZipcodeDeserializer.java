package nyc.c4q.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ZipcodeDeserializer implements JsonDeserializer<Zipcode> {
    @Override
    public Zipcode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jobject = (JsonObject) json;
        JsonArray loc = jobject.get("loc").getAsJsonArray();
        String _id = jobject.get("_id").getAsString();
        String city = jobject.get("city").getAsString();
        double[] location = {loc.get(0).getAsDouble(), loc.get(1).getAsDouble()};
        String state = jobject.get("state").getAsString();
        int pop = jobject.get("pop").getAsInt();

        return new Zipcode(_id, city, location, pop, state);
    }
}
