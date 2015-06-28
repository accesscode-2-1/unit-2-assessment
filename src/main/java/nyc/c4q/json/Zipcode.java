package nyc.c4q.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zipcode {

    @Expose
    @SerializedName("_id")
    public String id;
    @Expose
    public String city;
    @Expose
    public String[] loc;
    @Expose
    public String pop;
    @Expose
    public String state;

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String[] getLoc() {
        return loc;
    }

    public String getPop() {
        return pop;
    }

    public String getState() {
        return state;
    }
}
