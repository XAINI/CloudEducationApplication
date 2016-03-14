package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurriculumMaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_material);
    }

    public void backMyCurriculumPage(View view){
        Intent intent = new Intent(this, MyCurriculumActivity.class);
        startActivity(intent);
    }
}
