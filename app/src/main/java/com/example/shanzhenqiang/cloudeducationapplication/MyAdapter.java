package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanzhenqiang on 2016/3/17.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private CloudEducationHomeActivity ceh;

    public MyAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {

        return getDate().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(getDate().get(position).get("ItemTitle").toString());
        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
    }

    private ArrayList<HashMap<String, Object>> getDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();


        String[] data = new String[]{"PHP视频教程","C语言教程","C++基础","汇编语言从零开始","Java编程全套课程精讲","数据结构"};

        for (int i = 0; i < data.length; i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("ItemTitle",data[i]);
            listItem.add(map);
        }

        return listItem;
    }
}
