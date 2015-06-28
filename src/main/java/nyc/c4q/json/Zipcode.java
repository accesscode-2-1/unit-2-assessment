package nyc.c4q.json;

public class Zipcode {

    String id;
    int pop;
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

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}


