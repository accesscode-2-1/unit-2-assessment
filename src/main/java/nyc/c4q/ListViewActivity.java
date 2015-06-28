package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    public ArrayAdapter<String> adapter;
    public ListView listView;
    public EditText adapterCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        listView = (ListView) findViewById(R.id.list);
        adapterCount = (EditText) findViewById(R.id.adapterCount);

        adapter = new ArrayAdapter<String>(this, R.layout.listview_tile, R.id.title, COLORS) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =  (View) super.getView(position, convertView, parent);
                view.setBackgroundColor(Color.parseColor(COLORS[position]));
                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String color = COLORS[position];
                textLog.setText("You clicked on Item(position=" + position + ", color=" + color + ")");
            }
        });


    }
}
