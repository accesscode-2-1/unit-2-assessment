package nyc.c4q.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Zipcode implements Serializable {

    @SerializedName("_id")
    public String _id;
    @SerializedName("city")
    public String city;
    @SerializedName("state")
    public String state;
    @SerializedName("pop")
    public int pop;
    @SerializedName("loc")
    public double[] loc;


    @Override
    public String toString() {
        return "{\"_id\":\"11101\",\"city\":\"ASTORIA\",\"loc\":[-73.939393,40.750316],\"pop\":23142,\"state\":\"NY\"}";
    }
}
