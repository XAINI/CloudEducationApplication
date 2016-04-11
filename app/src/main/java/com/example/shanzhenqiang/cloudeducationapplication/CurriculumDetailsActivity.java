package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurriculumDetailsActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.shanzhenqiang.cloudeducationapplication.MASSAGE";
    private LinearLayout ll = null;
    private LinearLayout ll2 = null;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_details);

        ll = (LinearLayout) findViewById(R.id.footerId); //fetch footer layout id
        ll2 = (LinearLayout) findViewById(R.id.headerId); // fetch header layout id


        // set header title
        TextView textView = (TextView) ll2.findViewById(R.id.headerTitleTextViewId);
        textView.setText("IT互联网");


        // header's onclick event(back)
        final Intent backIntent = new Intent(this, CurriculumActivity.class);
        ImageButton backBtn = (ImageButton) ll2.findViewById(R.id.backId);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(backIntent);
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
            MyAdapter mAdapter = new MyAdapter(CurriculumDetailsActivity.this);
            mAdapter.setData(result);
            listView.setAdapter(mAdapter);

            final Intent intentListView = new Intent(CurriculumDetailsActivity.this, CurriculumMaterialActivity.class);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                    String message = "curriculumDetail";
                    intentListView.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intentListView);
                    Log.v("MyListViewBase", "你点击了ListView条目"+arg2);
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


    public void getInPHPDetail(View view) {
        Intent intent = new Intent(this, CurriculumMaterialActivity.class);
        String message = "curriculumDetail";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
