package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurriculumDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_details);
    }



    public void backCurriculum(View view){
        Intent intent = new Intent(this, CurriculumActivity.class);
        startActivity(intent);
    }

    public void curriculum(View view){
        Intent intent = new Intent(this, CurriculumActivity.class);
        startActivity(intent);
    }

    public void home(View view){
        Intent intent = new Intent(this, CloudEducationHomeActivity.class);
        startActivity(intent);
    }
}
