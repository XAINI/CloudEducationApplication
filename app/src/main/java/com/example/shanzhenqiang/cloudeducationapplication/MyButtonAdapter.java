package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanzhenqiang on 2016/3/18.
 */
public class MyButtonAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    public MyButtonAdapter(Context context){
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
            convertView = mInflater.inflate(R.layout.button_item, null);
            holder = new ViewHolder();
            holder.btn = (Button) convertView.findViewById(R.id.ItemBtn);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.btn.setText(getDate().get(position).get("ItemBtn").toString());
        return convertView;
    }

    public final class ViewHolder{
        public Button btn;
    }

    private ArrayList<HashMap<String, Object>> getDate(){
        ArrayList<HashMap<String, Object>> listView = new ArrayList<HashMap<String, Object>>();

        String[] data = new String[]{">编程语言",">移动开发",">产品设计",">WEB开发",">操作系统",">数据库",">产品运营",">数据结构",">计算机网络",">软件测试",">软件运维"};

        for (int i = 0; i < data.length; i ++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemBtn", data[i]);
            listView.add(map);
        }

        return listView;
    }
}
