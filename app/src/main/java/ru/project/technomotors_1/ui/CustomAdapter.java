package ru.project.technomotors_1.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.project.technomotors_1.R;


public class CustomAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater myLayoutInflater;
    private String[]myItem;
    int[]image;

    public CustomAdapter(Context context, String[] myItem, int[] image){
        this.myItem = myItem;
        this.image = image;
        myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return myItem.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = myLayoutInflater.inflate(R.layout.list_item_service_fragment,null);

        TextView tv = convertView.findViewById(R.id.item_service_text);
        tv.setText(myItem[position]);

        ImageView iv = convertView.findViewById(R.id.item_service_icon);
        iv.setImageResource(image[position]);

        return convertView;
    }
}
