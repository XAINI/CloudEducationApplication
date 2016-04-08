package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CloudEducationHomeActivity extends AppCompatActivity {

    private LinearLayout ll = null;
    private LinearLayout ll2 = null;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_education_home);

        ll = (LinearLayout) findViewById(R.id.footerId);// fetch footer layout id
        ll2 = (LinearLayout) findViewById(R.id.onlyHeaderId);// fetch header layout id

        // set header title
        TextView textView = (TextView) ll2.findViewById(R.id.onlyHeaderTitleTextViewId);
        textView.setText("首页");

        // fallback sign up
        final Intent signUp = new Intent(this, SignUpActivity.class);
        TextView fallbackRegister = (TextView) ll2.findViewById(R.id.fallBackRegister);
        fallbackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signUp);
            }
        });

        // footer's onclick event
        final Intent intent1 = new Intent(this, CloudEducationHomeActivity.class);
        final Intent intent2 = new Intent(this, CurriculumActivity.class);
        final Intent intent3 = new Intent(this, PersonalCenterActivity.class);

        ImageButton btn1 = (ImageButton) ll.findViewById(R.id.home_page);
        ImageButton btn2 = (ImageButton) ll.findViewById(R.id.allCurriculum);
        ImageButton btn3 = (ImageButton) ll.findViewById(R.id.myCenter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);
            }
        });


        // set ListView
        listView = (ListView) findViewById(R.id.lv);
        MyAdapter mAdapter = new MyAdapter(this);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        });


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
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i< jsonArray.length(); i++){
                    HashMap<String,Object> map = new HashMap<String, Object>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String path = jsonObject.getString("path");
                    String content = jsonObject.getString("content");

                    map.put("name", name);
                    listItem.add(map);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            listView = (ListView) findViewById(R.id.lv);
        }

        String test_get() throws Exception {
            String url = "http://192.168.100.3:3000/curriculums/fetch_curriculums";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }

        }

    }



    public void getInCurriculum(View view) {
        Intent intent = new Intent(this, CurriculumActivity.class);
        startActivity(intent);
    }
}
