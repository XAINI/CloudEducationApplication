package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//         User sign up
        Button btn = (Button) findViewById(R.id.sign_up);
        Button btnReset = (Button) findViewById(R.id.resetBtnId);

        // click sign up button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask task = new MyTask();
                task.execute();
            }
        });

        //click reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameId = (EditText) findViewById(R.id.accountEditTextId);
                EditText pwdId = (EditText) findViewById(R.id.pwdEditTextId);
                nameId.getText().clear();
                pwdId.getText().clear();
            }
        });

    }

    //     connect rails service invoke new users
    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
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
            if (result.equals("Successfully")) {
                listView.setTextColor(getResources().getColor(R.color.green));
                final Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                };
                timer.schedule(task, 1000 * 1);

            } else {
                listView.setTextColor(getResources().getColor(R.color.red));
            }
        }

        String post_to_service() throws IOException {
            String url = "http://192.168.100.3:3000/users/sign_up_from_android";
            OkHttpClient client = new OkHttpClient();

            EditText accountId = (EditText) findViewById(R.id.accountEditTextId);
            EditText passwordId = (EditText) findViewById(R.id.pwdEditTextId);
            RequestBody formBody = new FormBody.Builder()
                    .add("user[:name]", accountId.getText().toString())
                    .add("user[:password]", passwordId.getText().toString())
                    .build();
            Request request = new Request.Builder().url(url).post(formBody).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

    }

    public void checkSignUp(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}
