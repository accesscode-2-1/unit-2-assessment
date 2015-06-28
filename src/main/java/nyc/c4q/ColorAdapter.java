package nyc.c4q;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by c4q-anthonyf on 6/28/15.
 */
public class ColorAdapter extends BaseAdapter {

    private Context context;
    private String[] colors;
    private int itemHeight;
    private int numOfItems;

    private LayoutInflater inflater;

    public ColorAdapter(Context context, String[] colors, int itemHeight, int numOfItems) {
        this.context = context;
        this.colors = colors;
        this.itemHeight = itemHeight;
        this.numOfItems = numOfItems;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return numOfItems;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        String color = colors[i%colors.length];

        View row = inflater.inflate(R.layout.list_item_layout, null);
        TextView itemView = (TextView) row.findViewById(R.id.item_view);
        itemView.setBackgroundColor(Color.parseColor(color));
        itemView.setText(color);
        ViewGroup.LayoutParams params= itemView.getLayoutParams();
        if(itemHeight > 9){
            params.height=itemHeight * 5;
        }else{
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        itemView.setLayoutParams(params);

        return row;
    }
}
