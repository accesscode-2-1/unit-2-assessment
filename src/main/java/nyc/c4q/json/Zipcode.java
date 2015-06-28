package nyc.c4q.json;

public class Zipcode {

    String _id;
    String city;
    double loc;
    int pop;
    String state;

    public Zipcode(String id, String city, double loc, int pop, String state){
        this._id = id;
        this.city = city;
        this.loc = loc;
        this.pop = pop;
        this.state = state;


    }
}
