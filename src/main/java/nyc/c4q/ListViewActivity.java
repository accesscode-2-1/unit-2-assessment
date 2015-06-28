package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

import static nyc.c4q.R.color.background;

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
    public  ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        textLog = (TextView) findViewById(R.id.textLog);


        //I was trying to create a list adapter
       // listView = (ListView) findViewById(R.id.list);
        //ListView listView = new ListView(this);
//        listView= (ListView) findViewById(R.id.list);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,COLORS);
//        listView.setAdapter(adapter);
    }
}
