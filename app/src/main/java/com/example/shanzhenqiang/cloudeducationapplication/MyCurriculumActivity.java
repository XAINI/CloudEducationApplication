package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyCurriculumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_curriculum);
    }

    public void backMyHomePage(View view){
        Intent intent = new Intent(this, PersonalCenterActivity.class);
        startActivity(intent);
    }

    public void checkInPHPDetail(View view){
        Intent intent = new Intent(this, CurriculumMaterialActivity.class);
        startActivity(intent);
    }
}
