package org.tensorflow.lite.examples.detection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomViewDetectedList extends BaseAdapter {

    private final Activity context;
    ArrayList<HashMap<String, String>> data;

    public CustomViewDetectedList(Activity context,
                                ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.customviewdetectedlist, null);
            holder = new ViewHolder();
            holder.clv_object = (TextView) view.findViewById(R.id.clv_object);
            holder.clv_time = (TextView) view.findViewById(R.id.clv_time);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }

        HashMap<String, String> obj = data.get(position);

      //  Log.d("vt", "number " + obj.get("clv_number"));

        holder.clv_object.setText(obj.get("get_object"));
        holder.clv_time.setText(obj.get("get_time"));



        return view;
    }

    static class ViewHolder {
        TextView clv_object, clv_time;

    }
}