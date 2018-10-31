package com.simant.onlinenepal;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationActivity extends AppCompatActivity {

    // String API_KEY = "8190df9eb51445228e397e4185311a66"; // ### YOUE NEWS API HERE ###
    String API_KEY = "https://brainaigence.000webhostapp.com/notification.php";
    String NEWS_SOURCE = "simant";
    ListView listnews;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

    static final String KEY_HEADER = "nHeader";
    static final String KEY_DESCRIPTION = "nDescription";
    static final String KEY_TIME = "ntime";
    static final String KEY_URLTOIMAGE = "nImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Back button

        // Get a support ActionBar corresponding to this toolbar
        // Will back the activity to the main class
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        // Help to pass the class
        // Add meta in manifest file
        ab.setDisplayHomeAsUpEnabled(true);

        // back button end

        listnews = findViewById(R.id.api_notif);
        loader = findViewById(R.id.loader);
        listnews.setEmptyView(loader);


        if(Function.isNetworkAvailable(getApplicationContext()))
        {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        }else{
            Toast.makeText(getApplicationContext(), "Please check your internet connection and try again.", Toast.LENGTH_LONG).show();
        }

    }

    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String... args) {
            String xml = "";

            String urlParameters = "";
            xml = Function.excuteGet("https://brainaigence.000webhostapp.com/notification.php", urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("alert");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_HEADER, jsonObject.optString(KEY_HEADER).toString());
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION).toString());
                        map.put(KEY_TIME, jsonObject.optString(KEY_TIME).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());

                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Sorry, We are under maintainance. It may take an hours.", Toast.LENGTH_SHORT).show();
                }

                ListNotifAdapter adapter = new ListNotifAdapter(NotificationActivity.this, dataList);
                listnews.setAdapter(adapter);

                listnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {


                        HashMap<String, String> map = dataList.get(position);
                        Intent i = new Intent(NotificationActivity.this, DetailsActivity.class);
                        // i.putExtra("url",map.get(KEY_URL));

                        /*

                        i.putExtra("url",map.get(KEY_URL));
                        i.putExtra("title",map.get(KEY_TITLE));
                        i.putExtra("source",map.get(KEY_SOURCE));
                        i.putExtra("desc",map.get(KEY_DESCRIPTION));
                        startActivity(i);

                        */

                    }
                });


            }else {
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }



    }



}
