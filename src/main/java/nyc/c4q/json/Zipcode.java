package nyc.c4q.json;

public class Zipcode {
    public String id;
    public String city;
    public String state;
    public int pop;

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

    public String getField(String field){
        if(field.equalsIgnoreCase("_id")){
            return this.id;
        }else if(field.equalsIgnoreCase("city")){
            return this.city;
        }else if(field.equalsIgnoreCase("state")){
            return this.state;
        }else if(field.equalsIgnoreCase("pop")){
            return String.valueOf(this.pop);
        }
        else return null;
    }
}
