package nyc.c4q;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        ListView list = (ListView) findViewById(R.id.list);
        EditText adapterCount = (EditText) findViewById(R.id.adapterCount);

        ListAdapter adapter = new MyAdapter(this,COLORS);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.title);
                textLog.setText("You clicked on Item(position="+position+", color="+title.getText().toString()+")");
            }
        });
    }

    public class MyAdapter extends ArrayAdapter<String>{
        public MyAdapter(Context context, String[] objects) {
            super(context, 0, objects);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String singleColor = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_tile, parent, false);
            }
            TextView colorCode = (TextView) convertView.findViewById(R.id.title);
            colorCode.setText(singleColor);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(singleColor));
            convertView.setBackground(colorDrawable);
            return convertView;
        }

    }
}
