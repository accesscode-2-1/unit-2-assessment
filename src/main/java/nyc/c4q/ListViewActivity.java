package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
    public ArrayList<String> colors;
    public TextView textLog;
    public ListView listView;
    public EditText adapterCount;
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        textLog = (TextView) findViewById(R.id.textLog);
        adapterCount = (EditText) findViewById(R.id.adapterCount);
        count = COLORS.length;
        colors = getColors(count);

        final ColorAdapter<String> colorsAdapter =
                new ColorAdapter<>(this, R.layout.custom_list_item, colors, textLog);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(colorsAdapter);
        listView.setItemsCanFocus(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textLog.setText("You clicked on Item(position=" + position + ", color=" + ListViewActivity.COLORS[position] + ")");
            }

        });

        adapterCount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (isValidInput(s.toString())) {
                    count = Integer.valueOf(s.toString());
                    colors = getColors(count);
                    colorsAdapter.clear();
                    colorsAdapter.addAll(colors);
                }
            }
        });

    }

    public ArrayList<String> getColors(int count) {
        ArrayList<String> colors = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            colors.add(COLORS[i % 10]);
        }

        return colors;
    }

    public boolean isValidInput(String input) {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
