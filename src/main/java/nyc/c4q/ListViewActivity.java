package nyc.c4q;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

        ListView list = (ListView) findViewById(R.id.list);
//        CustomAdapter adapter = new CustomAdapter(this,R.layout.custom_listview,COLORS);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, COLORS);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ColorDrawable bgColor = (ColorDrawable) view.getBackground();
                textLog.setText("You clicked on Item(position=" + position + ", color="  + bgColor.getColor()+ ")");
            }
        });



    }
}
