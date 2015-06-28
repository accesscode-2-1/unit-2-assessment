package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
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
        final ListView list = (ListView) findViewById(R.id.list);
        EditText adapterCount = (EditText) findViewById(R.id.adapterCount);

        ListAdapter adapter = list.getAdapter();
        adapterCount.length();
        int i = 0;
        adapterCount.setText(Integer.toString(i));
        adapterCount.setInputType(InputType.TYPE_CLASS_PHONE);

    }
}