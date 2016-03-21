package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.util.Log;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.button_item, null);
            holder = new ViewHolder();
            holder.btn = (Button) convertView.findViewById(R.id.ItemBtn);
            holder.btn1 = (Button) convertView.findViewById(R.id.SecondBtn);
            holder.btn2 = (Button) convertView.findViewById(R.id.ThirdBtn);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println(position);
        Log.v("SecondBtn","SecondBtn数据"+getDate().get(5));
        holder.btn.setText(getDate().get(position).get("ItemBtn").toString());
        holder.btn1.setText(getDate().get(4).get("SecondBtn").toString());
        holder.btn2.setText(getDate().get(8).get("ThirdBtn").toString());
        return convertView;
    }

    public final class ViewHolder{
        public Button btn;
        public Button btn1;
        public Button btn2;
    }

    private ArrayList<HashMap<String, Object>> getDate(){
        ArrayList<HashMap<String, Object>> listView = new ArrayList<HashMap<String, Object>>();

        String[] data = new String[]{">编程语言",">移动开发",">产品设计",">WEB开发"};
        String[] data1 = new String[]{">操作系统",">数据库",">产品运营",">数据结构"};
        String[] data2 = new String[]{">计算机网络",">软件测试",">软件运维","Hello"};

        for (int i = 0; i < data.length; i ++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemBtn", data[i]);
            listView.add(map);
        }

        for (int i = 0; i < data1.length; i ++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("SecondBtn", data1[i]);
            listView.add(map);
        }

        for (int i = 0; i < data2.length; i ++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ThirdBtn", data2[i]);
            listView.add(map);
        }

        return listView;
    }
}
