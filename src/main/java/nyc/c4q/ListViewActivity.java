package nyc.c4q;

import android.app.Activity;
import android.app.ListActivity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
    public EditText adapterCount;
    public ListView list;
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);

        adapterCount = (EditText) findViewById(R.id.adapterCount);
        list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, COLORS );
        list.setAdapter(adapter);

//        for (int i = 0; i < COLORS.length; i++) {
//
//            list.indexOfChild(i).setBackground(COLORS);
//        }

    }
}
