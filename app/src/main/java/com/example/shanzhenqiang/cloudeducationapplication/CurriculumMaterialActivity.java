package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CurriculumMaterialActivity extends AppCompatActivity {

    private LinearLayout ll = null;
    private LinearLayout ll2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_material);

        ll = (LinearLayout) findViewById(R.id.footerId); // fetch footer layout id
        ll2 = (LinearLayout) findViewById(R.id.headerId); // fetch header layout id

        // set header title
        TextView textView = (TextView) ll2.findViewById(R.id.headerTitleTextViewId);
        textView.setText("PHP视频教程");

        // header's onclick event
        final Intent backIntent = new Intent(this, CurriculumDetailsActivity.class);
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
    }

    public void backMyCurriculumPage(View view) {
        Intent intent = new Intent(this, MyCurriculumActivity.class);
        startActivity(intent);
    }
}
