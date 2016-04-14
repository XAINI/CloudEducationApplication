package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btn = (Button) findViewById(R.id.signInBtnId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySignInTask signInTask = new MySignInTask();
                signInTask.execute();
            }
        });
    }



//  登录的 Task
    public class MySignInTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String result;
            try {
                result = get_uer_info();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ArrayList<HashMap<String, Object>> listItem = formatConvert(result);
            boolean compareResult = verification(listItem);
            if (compareResult == true){
                Intent intent = new Intent(SignInActivity.this, CloudEducationHomeActivity.class);
                startActivity(intent);
            }else{
                TextView textView = (TextView) findViewById(R.id.SignInPromptId);
                textView.setText("您的账号与密码不符");
            }
        }

        // 验证用户信息（用户名和密码是否存在）
        public boolean  verification(ArrayList<HashMap<String, Object>> listItem){
            EditText accountId = (EditText) findViewById(R.id.signInAccountId);
            EditText pwdId = (EditText) findViewById(R.id.signInPwdId);
            HashMap<String, Object> map = new HashMap<String, Object>();

            String account = accountId.getText().toString();
            String pwd = pwdId.getText().toString();
            map.put("name", account);
            map.put("password", pwd);
            if (listItem.contains(map))
                return true;
            else
                return false;
        }
        // 格式转换 将返回的 json 转换成 ArrayList
        public ArrayList<HashMap<String, Object>> formatConvert(String result) {
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String pwd = jsonObject.getString("password");
                    map.put("name", name);
                    map.put("password", pwd);
                    listItem.add(map);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listItem;
        }

    }

    String get_uer_info() throws Exception {
        String url = "http://192.168.100.3:3000/users/fetch_users_info";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }

    }



    public void backRegisterPage(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
