package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

//        mgr = new DBManager(this);


        //获取text中 用户填入的值
        listView = (ListView) findViewById(R.id.listView);
        Button btn = (Button) findViewById(R.id.sign_up);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask task = new MyTask();
                task.execute();
            }
        });

    }

    // connect rails service invoke new users
    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            String  result = null;
            try {
                result = post_to_service();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            TextView listView = (TextView) findViewById(R.id.thisTestView);
            listView.setText(result);

        }

        String post_to_service() throws IOException {
            String url = "http://192.168.100.3:3000/users/sign_up_from_android";
            EditText accountId = (EditText) findViewById(R.id.accountEditTextId);
            EditText passwordId = (EditText) findViewById(R.id.pwdEditTextId);

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("user[:name]", accountId.getText().toString())
                    .add("user[:password]", passwordId.getText().toString())
                    .build();
            Request request = new Request.Builder().url(url).post(formBody).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
//            if (!response.isSuccessful())throw new IOException("Unexpected code " + response);
        }

    }


    // test SQLite
//    protected void onDestroy(){
//        super.onDestroy();
//        mgr.closeDB();
//    }
//
//    public void signUpUser(View view){
//        EditText editAccountText = (EditText) findViewById(R.id.accountEdittext);
//        String account = editAccountText.getText().toString();
//        EditText editPasswordText = (EditText) findViewById(R.id.pwdEdittext);
//        String password = editPasswordText.getText().toString();
//
//        ArrayList<User> users = new ArrayList<User>();
//
//        User user = new User(account,password);
//        users.add(user);
//
//        mgr.add(users);
//
//        Intent intent = new Intent(this, SignInActivity.class);
//        startActivity(intent);
//    }

//    public void query(View view){
//        List<User> users = mgr.query();
//        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        for (User user : users){
//            HashMap<String, String> map = new HashMap<>();
//            map.put("account", user.account);
//            map.put("password", user.password );
//            list.add(map);
//        }
//
//        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, new String[]{"account","password"}, new int[]{android.R.id.text1, android.R.id.text2});
//        listView.setAdapter(adapter);
//    }


    public void checkSignUp(View view){
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }
}
