package nyc.c4q.json;


public class Zipcode {

    public String _id;
    public String city;
    public String state;
    public double [] loc;
    public int pop;

    public Zipcode (String _id, String city, double [] loc, int pop, String state)  {

        this._id = _id;
        this.city = city;
        this.state = state;
        this.loc = loc;
        this.pop = pop;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double[] getLoc() {
        return loc;
    }

    public void setLoc(double[] loc) {
        this.loc = loc;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }
}
