package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanzhenqiang on 2016/3/17.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private String result = null;

    public MyAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return getData().size();
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
            holder.follow = (Button) convertView.findViewById(R.id.ItemImBtn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(getData().get(position).get("ItemTitle").toString());
        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("MyListViewBase", "你点击了按钮" + position);
            }
        });
        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public Button follow;
    }

    public String setData(String result) {
        this.result = result;
        return result;
    }

    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                final String name = jsonObject.getString("name");
                map.put("ItemTitle", name);
                listItem.add(map);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listItem;
    }
}
