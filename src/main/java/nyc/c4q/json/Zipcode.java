package nyc.c4q.json;

public class Zipcode {
    public String _id;
    public String city;
    public String state;
    public int pop;
    public double[] loc;

    public double _long;
    public double _lat;

    @Override
    public String toString(){
        return String.format("Zipcode(_id=%s,city=%s,state=%s,pop=%s,loc=%s)",
                _id, city, state, pop, loc);
    }
}
