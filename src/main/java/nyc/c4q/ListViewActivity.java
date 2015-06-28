package nyc.c4q;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

//array of colors
    public static final String[] COLORS = {
            "#142b44",
            "#1d508d",
            "#297cbb",
            "#288ad6",
            "#0fdebd",
            "#16c98d",
            "#feef6d",
            "#ffc83f",
            "#fa5e5b",
            "#bf538d"
    };

    //variables
    private ListView list;
    private ArrayAdapter arrayAdapter;
    public TextView textLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //declaring variables
        textLog = (TextView) findViewById(R.id.textLog);
        EditText adapterCount = (EditText) findViewById(R.id.adapterCount);
        list = (ListView) findViewById(R.id.list);

        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, COLORS);
        list.setAdapter(arrayAdapter);

        //change the color for the division
        // list.setDivider(new ColorDrawable());
        ColorDrawable sage = new ColorDrawable(this.getResources().getColor(R.color.sage));
        list.setDivider(sage);
        list.setDividerHeight(2);
    }

        /*TODO: For test07- was I suppose to ColorDrawable to show the string values of the array into the color background (ex: the "#color" should show a color in one of the row backgrounds*/
//TODO: For test08- textLog should say "You clicked on Item(position=%s (item within the array), color=%s (color of the item)- Not sure within the listview or on the listview xml
//TODO: For test09 and 10- not sure what to do with the Inputs. Is there a for loop with boolean statements in it?


//    The Idea was to make a View method that can use if statements to get the color from text to show color
    //I got this info at http://www.survivingwithandroid.com/2013/04/android-listview-background-row-style.html

//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//
//        PlanetHolder holder = new PlanetHolder();
//
//        // First let's verify the convertView is not null
//        if (convertView == null) {
//            // This a new view we inflate the new layout
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = inflater.inflate(R.layout.row_layout, null);
//            // Now we can fill the layout with the right values
//            TextView tv = (TextView) v.findViewById(R.id.name);
//
//            holder.planetNameView = tv;
//
//            v.setTag(holder);
//
//            v.setBackgroundResource(R.drawable.rounded_corner);
//        }
//        else
//            holder = (PlanetHolder) v.getTag();
//
//        Planet p = planetList.get(position);
//        holder.planetNameView.setText(p.getName());
//
//        return v;
//    }

}