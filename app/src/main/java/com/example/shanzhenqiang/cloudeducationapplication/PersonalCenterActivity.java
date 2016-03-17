package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PersonalCenterActivity extends AppCompatActivity {

    private LinearLayout ll = null;
    private LinearLayout ll2 = null;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        ll = (LinearLayout) findViewById(R.id.footerId); // fetch footer layout id
        ll2 = (LinearLayout) findViewById(R.id.onlyHeaderId); // fetch header layout id

        // set header title
        TextView textView = (TextView) ll2.findViewById(R.id.onlyHeaderTitleTextViewId);
        textView.setText("我的主页");

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
                Log.v("MyListViewBase", "你点击了ListView条目"+arg2);
            }
        });
    }

    public void checkInMyMessage(View view) {
        Intent intent = new Intent(this, MyMessageActivity.class);
        startActivity(intent);
    }

    public void getInMyCurriculum(View view) {
        Intent intent = new Intent(this, MyCurriculumActivity.class);
        startActivity(intent);
    }

}
