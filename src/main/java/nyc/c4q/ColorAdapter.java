package nyc.c4q;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

/**
 * Created by Ramona Harrison
 * on 6/28/15.
 */
public class ColorAdapter<String> extends ArrayAdapter<String> {
    // View lookup cache
    private static class ViewHolder {
        LinearLayout background;

    }

    public ColorAdapter(Context context, int layout, String[] colors) {
        super(context, layout, colors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String color = getItem(position);

        // Inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);
            viewHolder.background = (LinearLayout) convertView.findViewById(R.id.background);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set view background color
        viewHolder.background.setBackgroundColor(Color.parseColor((java.lang.String) color));

        return convertView;
    }
}
