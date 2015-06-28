package nyc.c4q.json;

public class Zipcode {
    private int id, pop;
    String city;
    String state;

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
        this.id = id;
    }



//    assertThat(result, containsString("\"_id\":\"11101\""));
//    assertThat(result, containsString("\"pop\":23142"));
//    assertThat(result, containsString("\"city\":\"ASTORIA\""));
//    assertThat(result, containsString("\"state\":\"NY\""));
//    assertThat(result, containsString("\"loc\":[-73.939393,40.750316]"));
}
