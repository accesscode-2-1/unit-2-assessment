package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
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
    public EditText adapterCount;
    public ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        adapterCount = (EditText) findViewById(R.id.adapterCount);
        adapterCount.setInputType(InputType.TYPE_CLASS_PHONE);
        list = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> colors = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, COLORS);

        list.setAdapter(colors);

        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            View v = colors.getView(i, null, list);
            v.setBackgroundColor(Color.parseColor(COLORS[i]));
        }
    }
}
