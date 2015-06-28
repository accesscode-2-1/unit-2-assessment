package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

    ListView listview;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        View v;

        //get list object from xml
        listview = (ListView) findViewById(R.id.list);
        textLog = (TextView) findViewById(R.id.textLog);

        //define a new adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COLORS);
        //assign adapter to listview
        listview.setAdapter(adapter);
        //getCount() returns you a count of items in Adapter, which is total in list.
        if (adapter.getCount() == COLORS.length) {
            return;
        }
//        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
//            v = listview.getChildAt(i);
//
//            if (v.getBackground() instanceof ColorDrawable) {
//                ColorDrawable background = (ColorDrawable) v.getBackground();
//                background.getColor(Color.parseColor(ListViewActivity.COLORS[i]));
//            }
    }

}
