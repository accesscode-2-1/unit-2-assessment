package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    public ListView listView;
    public EditText adapterCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        listView = (ListView) findViewById(R.id.list);
        adapterCount=(EditText) findViewById(R.id.adapterCount);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, R.id.textLog, COLORS);
        listView.setAdapter(adapter);

        for(int i=0;i<COLORS.length;i++){
            adapterCount.setText(Integer.toString(i));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textLog.setText("You clicked on Item(position=" + position + ", color=" + COLORS[position]+")");

            }

        });


    }


}