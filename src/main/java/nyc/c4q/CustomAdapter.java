package nyc.c4q;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by kadeemmaragh on 6/28/15.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    String[] listOfColors;
    LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, String[] items) {
        super(context, resource);
        listOfColors = items;
        inflater = ((Activity)context).getLayoutInflater();
    }

    private static class ViewHolder {
        TextView tv;
    }

    public int getCount(){
        return listOfColors.length;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listview_tile, null);

            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView)convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder)convertView.getTag();
        ColorDrawable background = (ColorDrawable) viewHolder.tv.getBackground();
        background.setColor(Color.parseColor(listOfColors[position]));

        return convertView;
    }

}
