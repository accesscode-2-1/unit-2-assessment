package nyc.c4q;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, COLORS);
        list.setAdapter(listAdapter);




        for (int i = 0; i < COLORS.length; i++) {
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textLog.setText("You clicked on Item(position=" + i + ", color=" + COLORS[i] + ")");

                    view.setBackgroundColor(Color.parseColor(COLORS[i]));

                }
            });

            View v = listAdapter.getView(i, null, list);
            ColorDrawable background = new ColorDrawable();
            background.setColor(Color.parseColor(COLORS[i]));
            v.setBackground(background);

        }










            // TODO: FINISH !!
//        list.performItemClick(View v, int position, long id) {
//            textLog.setText("You clicked on Item(" + position=%s + "," + color=%s + ")");
//        }



    }
}
