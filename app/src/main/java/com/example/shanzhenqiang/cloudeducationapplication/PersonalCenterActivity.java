package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PersonalCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
    }

    public void checkInMyMessage(View view){
        Intent intent = new Intent(this, MyMessageActivity.class);
        startActivity(intent);
    }

    public void getInMyCurriculum(View view){
        Intent intent = new Intent(this, MyCurriculumActivity.class);
        startActivity(intent);
    }

    public void backPlatformHome(View view){
        Intent intent = new Intent(this, CloudEducationHomeActivity.class);
        startActivity(intent);
    }
}
