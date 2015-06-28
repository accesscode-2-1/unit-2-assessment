package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);

        ColorAdapter<String> colorsAdapter =
                new ColorAdapter<>(this, R.layout.custom_list_item, COLORS);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(colorsAdapter);
    }

    public void changeText(View v) {
        int i = listView.getPositionForView(v);
        textLog.setText("You clicked on Item(position=" + i + ", color=" + ListViewActivity.COLORS[i] + ")");
    }
}
