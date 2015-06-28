package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    public ListView list;
    public EditText itemHeight;
    public EditText numOfItems;

    public int height;
    public int itemAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        list = (ListView) findViewById(R.id.list);

        height = 0;
        itemAmount = 0;

        itemHeight = (EditText) findViewById(R.id.input_item_height);
        numOfItems = (EditText) findViewById(R.id.input_num_items);

        itemHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String text = itemHeight.getText().toString();
                int defaultHeight = 0;
                try {
                    if(text.equals("")){
                        height = 0;
                    }else {
                        defaultHeight = Integer.parseInt(text);
                        height = defaultHeight;
                        list.setAdapter(new ColorAdapter(getApplicationContext(), COLORS, defaultHeight, itemAmount));
                    }
                }catch(NumberFormatException e){

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        numOfItems.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String text = numOfItems.getText().toString();
                int defaultAmount = 0;
                try {
                    if(text.equals("")){
                        itemAmount = 0;
                    }else {
                        defaultAmount = Integer.parseInt(text);
                        itemAmount = defaultAmount;
                        list.setAdapter(new ColorAdapter(getApplicationContext(), COLORS, height, defaultAmount));
                    }
                }catch(NumberFormatException e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tView = (TextView) view.findViewById(R.id.item_view);
                textLog.setText("You clicked on Item(position=" + i + ", color=" + tView.getText().toString() + ")");
            }
        });
    }
}
