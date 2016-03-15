package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CloudEducationHomeActivity extends AppCompatActivity {

    private LinearLayout ll = null;
    private LinearLayout ll2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_education_home);

        ll = (LinearLayout) findViewById(R.id.footerId);// fetch footer layout id
        ll2 = (LinearLayout) findViewById(R.id.onlyHeaderId);// fetch header layout id

        // set header title
        TextView textView = (TextView) ll2.findViewById(R.id.onlyHeaderTitleTextViewId);
        textView.setText("首页");

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
    }

    public void getInCurriculum(View view) {
        Intent intent = new Intent(this, CurriculumActivity.class);
        startActivity(intent);
    }
}
