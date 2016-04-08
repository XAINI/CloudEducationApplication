package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    /*public final static String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";*/

    private DBManager mgr;
    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        listView = (ListView) findViewById(R.id.listView);
        mgr = new DBManager(this);

//        Button btn = (Button) findViewById(R.id.testId);
//        btn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//                MyTask myTask = new MyTask();
//                myTask.execute();
//            }
//        });

    }

    protected void onDestroy(){
        super.onDestroy();
        mgr.closeDB();
    }

    public void signUpUser(View view){
        EditText editAccountText = (EditText) findViewById(R.id.accountEdittext);
        String account = editAccountText.getText().toString();
        EditText editPasswordText = (EditText) findViewById(R.id.pwdEdittext);
        String password = editPasswordText.getText().toString();

        ArrayList<User> users = new ArrayList<User>();

        User user = new User(account,password);
        users.add(user);

        mgr.add(users);

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void query(View view){
        List<User> users = mgr.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (User user : users){
            HashMap<String, String> map = new HashMap<>();
            map.put("account", user.account);
            map.put("password", user.password );
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, new String[]{"account","password"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }


    public void checkSignUp(View view){
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }
}
