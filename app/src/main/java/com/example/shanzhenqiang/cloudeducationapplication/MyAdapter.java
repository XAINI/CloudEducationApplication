package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shanzhenqiang on 2016/3/17.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

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
            holder.follow = (Button) convertView.findViewById(R.id.ItemImBtn);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(getDate().get(position).get("ItemTitle").toString());
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

    private ArrayList<HashMap<String, Object>> getDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        String dataCurriculum = null;

        MyTask myTask = new MyTask();
        myTask.execute();

        String[] data = new String[]{"PHP视频教程","C语言教程","C++基础","汇编语言从零开始","Java编程全套课程精讲","数据结构"};

        for (int i = 0; i < data.length; i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("ItemTitle",data[i]);
            listItem.add(map);
        }

        return listItem;
    }



    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String result = null;

            try {
                result = test_get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        String test_get() throws Exception {
            String url = "http://192.168.100.3:3000/curriculums/fetch_curriculums";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                System.out.println("This is http function===" + response.body().string());
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }

        }
    }
}
