package nyc.c4q.json;

import org.json.JSONException;
import org.json.JSONObject;

public class Zipcode {
    public int _id, pop;
    public String city;
    public String state;

    private String JSON_id, JSON_pop,
    JSON_city, JSON_loc;


    public void setPop(int pop) {
        this.pop = pop;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    String loc;
    public Zipcode(int id){
        this._id = id;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_id, _id);
        json.put(JSON_city, city);
        json.put(JSON_loc, loc);
        json.put(JSON_pop, pop);
        return json;
    }

}
