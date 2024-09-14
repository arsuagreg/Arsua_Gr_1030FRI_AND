package com.example.arsua_to_do_list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private List<ToDoItem> items;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, List<ToDoItem> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView textView = convertView.findViewById(R.id.ToDoText);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        ToDoItem item = items.get(position);

        checkBox.setChecked(item.isChecked());
        textView.setText(item.getDescription());
        imageView.setImageResource(R.mipmap.ic_launcher);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> item.setChecked(isChecked));

        return convertView;
    }
}