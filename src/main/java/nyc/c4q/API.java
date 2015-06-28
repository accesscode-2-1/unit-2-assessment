package nyc.c4q;

/**
 * Created by c4q-marbella on 6/28/15.
 */
public class API {
    String id;
    String pop;
    String city;
    String state;
    String lat_;
    String long_;

    public String getLat_() {
        return lat_;
    }

    public void setLat_(String lat_) {
        this.lat_ = lat_;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLong_() {
        return long_;
    }

    public void setLong_(String long_) {
        this.long_ = long_;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
