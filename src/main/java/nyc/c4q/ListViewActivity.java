package nyc.c4q;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity implements ListAdapter {

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
    int count = COLORS.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        String[] COLORS = {
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
        if (getCount() != count){
            Log.e("something's wrong", "fix the count");
        }

        //Get list from xml
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COLORS );
        ColorDrawable colorDrawable = new ColorDrawable();
        list.setAdapter(adapter);
        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            View v = adapter.getView(i, null, list);
//            for (int n = 0; n < colorDrawable.colours.length; n++) {
//                v.setBackgroundColor(n);
//            }
            if (i == 0){
                View viewZero =getView(0, v, list);
                viewZero.setBackgroundColor(colorDrawable.one);
            }
        }

    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
