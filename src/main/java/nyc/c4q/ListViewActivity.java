package nyc.c4q;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewActivity extends Activity {
    ListView list;

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
    public EditText adapterCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        adapterCount = (EditText) findViewById(R.id.adapterCount);
        list = (ListView) findViewById(R.id.list);

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, COLORS);
    list.setAdapter(adapter);
   // list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            // ListView Clicked item index
//            int itemPosition = position;
//
//            // ListView Clicked item value
//            String itemValue = (String) list.getItemAtPosition(position);
//
//            // Show Alert
//            Toast.makeText(getApplicationContext(),
//                    "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                    .show();
//
//            }
//        }
    }
}
