package com.example.shanzhenqiang.cloudeducationapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shanzhenqiang on 2016/4/7.
 */
public class MyTask extends AsyncTask<String, Integer, String> {

    private TextView textView;

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
        Log.v("IWantKoKnowResultStruct", "ThisIsResult===="+result);
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
