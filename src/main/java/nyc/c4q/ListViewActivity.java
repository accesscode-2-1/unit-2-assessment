package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COLORS);
        listView.setAdapter(adapter);

         for (int i = 0; i <= listView.getLastVisiblePosition() - listView.getFirstVisiblePosition(); i++)  {
            View v = listView.getChildAt(i);
         //for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            //View v = adapter.getView(i, null, list);

            for (String color : COLORS){
                Log.d("|||", color);
                //int colorInt = Integer.parseInt(color);
                v.setVisibility(View.VISIBLE);
                v.setBackgroundColor(Color.parseColor(color));
                //v.setBackgroundColor();
            }

        }
    }
}


