package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
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
        MyTask task = new MyTask();
        task.execute();

        // curriculum label
        MyLabelTask myLabelTask = new MyLabelTask();
        myLabelTask.execute();

        ImageButton menuId = (ImageButton) findViewById(R.id.menuId);
        menuId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openOptionsMenu();
            }
        });
    }


    //创建Menu菜单
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cloud_education_home_menu, menu);
        return true;
    }

    //点击Menu菜单选项响应事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                break;
            case 2:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onOptionsMenuClosed(Menu menu) {

    }

    //菜单被显示之前的事件
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cloud_education_home_menu,menu);
        return super.onPrepareOptionsMenu(menu);
    }










    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String result;
            try {
                result = test_get();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            listView = (ListView) findViewById(R.id.lv);
            MyAdapter mAdapter = new MyAdapter(CloudEducationHomeActivity.this);
            mAdapter.setData(result);
            listView.setAdapter(mAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                }
            });

        }

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


    public class MyLabelTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String result;
            try {
                result = get_curriculum_label_info();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ArrayList<HashMap<String, Object>> listItem = formatConvert(result);

            System.out.println(listItem);
        }


        // 格式转换 将返回的 json 转换成 ArrayList
        public ArrayList<HashMap<String, Object>> formatConvert(String result) {
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String label = jsonObject.getString("label");
                    map.put("label", label);
                    listItem.add(map);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listItem;
        }

    }

    String get_curriculum_label_info() throws Exception {
        String url = "http://192.168.100.3:3000/curriculum_labels/fetch_curriculum_labels";
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
