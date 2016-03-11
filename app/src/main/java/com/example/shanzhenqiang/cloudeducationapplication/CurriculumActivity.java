package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurriculumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
    }


    public void courseDetails(View view){
        Intent intent =new Intent(this, CurriculumDetailsActivity.class);
        startActivity(intent);
    }

    public void backEducationHome(View view){
        Intent intent = new Intent(this, CloudEducationHomeActivity.class);
        startActivity(intent);
    }

    public void checkInCurriculum(View view){
        Intent intent = new Intent(this, CurriculumActivity.class);
        startActivity(intent);
    }

    public void checkInHome(View view){
        Intent intent = new Intent(this, CloudEducationHomeActivity.class);
        startActivity(intent);
    }
}
