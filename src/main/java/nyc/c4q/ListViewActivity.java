package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);

        initializeListViewWithAdapter();

    }

    //todo:continue here
    public void initializeListViewWithAdapter() {
        final ListView list = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, COLORS);
        Log.d("adapter.getcount()", " " + adapter.getCount());


//        for(int i = 0; i < COLORS.length; i++){
//            View v = adapter.getView(i, null, list);
//            v.setBackgroundColor(Color.parseColor(COLORS[i]));
//        }

        ListAdapter lAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return COLORS.length;
            }

            @Override
            public Object getItem(int i) {
                return COLORS[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {


                //view.setBackgroundColor(Color.parseColor(COLORS[i]));

                //((TextView) view).setText(COLORS[i]);

                return view;
            }
        };


        list.setAdapter(lAdapter);


    }

}
