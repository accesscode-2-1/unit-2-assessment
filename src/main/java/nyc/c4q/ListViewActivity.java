package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
        ListView listview = (ListView) findViewById(R.id.list);

        CustomAdapter listadapter = new CustomAdapter();
        listview.setAdapter(listadapter);
    }

    private class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return COLORS.length;
        }

        @Override
        public Object getItem(int position) {

            return COLORS[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = getLayoutInflater().inflate(R.layout.listview_tile, null);

               //convertView.setBackgroundResource(Color.parseColor(COLORS[position]));  //fixme
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textLog.setText("You clicked on Item " + COLORS[position]);
                }
            });


            return convertView;
        }

    }



}
