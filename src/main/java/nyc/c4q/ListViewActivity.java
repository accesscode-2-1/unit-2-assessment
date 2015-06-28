package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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
    public ListView list;
    public ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        textLog = (TextView) findViewById(R.id.textLog);
        list = (ListView) findViewById(R.id.list);


        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_multiple_choice,
                COLORS);
        list.setAdapter(adapter);

        for (int i = 0; i < COLORS.length; i++) {
            View v = adapter.getView(i, null, list);
            v.setBackgroundColor(Color.parseColor(COLORS[i]));
        }
    }



}
