package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ListViewActivity extends Activity {

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
    public TextView textLog;
    //ListView listOfColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);

        //listOfColors = (ListView) findViewById(R.id.list);
        //ArrayList<String> colors = new ArrayList<String>();


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this, R.layout.listview_tile,COLORS);
        //listOfColors.setAdapter(adapter);


    }


}
