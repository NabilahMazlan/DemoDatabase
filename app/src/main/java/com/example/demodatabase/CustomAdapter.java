package com.example.demodatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    ArrayList<Task> taskList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Task> objects){
        super(context,resource, objects);

        this.parent_context = context;
        taskList = objects;
    }



    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView, @NonNull ViewGroup parent){
        Task items = taskList.get(position);

        if (convertView ==  null) {
            LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview, null, true);
        }
        TextView tvID = convertView.findViewById(R.id.textViewID);
        TextView tvDate = convertView.findViewById(R.id.textViewDate);
        TextView tvDesc = convertView.findViewById(R.id.textViewDesc);

        tvDate.setText(items.getDate());
        tvID.setText(String.valueOf(items.getId()));
        tvDesc.setText(items.getDescription());



        return convertView;


    }
}
