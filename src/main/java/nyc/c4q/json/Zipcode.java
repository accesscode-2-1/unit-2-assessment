package nyc.c4q.json;

import java.util.Arrays;

public class Zipcode {

    public String _id, city, state;
    public int pop;
    public double[] loc;

    public Zipcode(String _id, String city, String state, int pop, double[] loc) {
        this._id = _id;
        this.city = city;
        this.state = state;
        this.pop = pop;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "{\"_id\":\"" + _id + "\",\"city\":\"" + city + "\",\"state\":\"" + state
                + "\",\"pop\":\"" + pop + "\",\"loc\":\"" + Arrays.toString(loc).replaceAll(" ", "") +"\"}";
    }
}