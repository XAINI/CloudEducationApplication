package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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

        Button btn = (Button) findViewById(R.id.testId);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        });

    }

    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String result = null;

            try {
                result = test_get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i< jsonArray.length(); i++){
                    HashMap<String,Object> map = new HashMap<String, Object>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String path = jsonObject.getString("path");
                    String content = jsonObject.getString("content");

                    map.put("name", name);
                    listItem.add(map);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            System.out.println(listItem);
            textView = (TextView) findViewById(R.id.testView);
            textView.setText(result);
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
