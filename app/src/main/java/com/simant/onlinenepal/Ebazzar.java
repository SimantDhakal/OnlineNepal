package com.simant.onlinenepal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Ebazzar extends AppCompatActivity {

    String API_KEY = "https://brainaigence.000webhostapp.com/eBazzar.php";
    String NEWS_SOURCE = "simant";
    ListView listView;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_ITEM = "कृषि उपज";
    static final String KEY_QUANTITY = "ईकाइ";
    static final String KEY_LEAST = "न्यूनतम";
    static final String KEY_MAXIMUM = "अधिकतम";
    static final String KEY_MINIMUM = "औसत";
    static final String KEY_URL = "image";
    static final String KEY_URLTOIMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebazzar);

        // solved bug 2 lines and actionbar import
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listnews2);
        loader = findViewById(R.id.loader);
        listView.setEmptyView(loader);

        if(Function.isNetworkAvailable(getApplicationContext()))
        {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        }else{
           // Toast.makeText(getApplicationContext(), "Please check your internet connection and try again.", Toast.LENGTH_LONG).show();
            existMode();
        }


    }

    // back to mainactivity
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String... args) {
            String xml = "";

            String urlParameters = "";
            xml = Function.excuteGet("https://brainaigence.000webhostapp.com/eBazzar.php", urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("kalimati");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_ITEM, jsonObject.optString(KEY_ITEM).toString());
                        map.put(KEY_QUANTITY, jsonObject.optString(KEY_QUANTITY).toString());
                        map.put(KEY_LEAST, jsonObject.optString(KEY_LEAST).toString());
                        map.put(KEY_MAXIMUM, jsonObject.optString(KEY_MAXIMUM).toString());
                        map.put(KEY_MINIMUM, jsonObject.optString(KEY_MINIMUM).toString());
                        map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Sorry, We are under maintainance. It may take an hours.", Toast.LENGTH_SHORT).show();
                }

                ListTrateAdapter adapter = new ListTrateAdapter(Ebazzar.this, dataList);
                listView.setAdapter(adapter);


            }else{
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }



    }


    private void existMode() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ebazzar.this);

        // Hanlde new AlertDialog.Builder(HomeFragment.this); into this.getContext();

        alertDialogBuilder
                .setMessage("Please check your internet connection or visit the offline mode.")
                .setCancelable(false)
                .setPositiveButton("Offline Mode",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // startActivity(new Intent(getContext(), LibraryFragment.class));
                                // handle getApplicationContext() to getContext() in fragment .java
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //  finish();
                                finish();
                                // Handle onClick event in fragment layout ot exit
                                // handle above offline capabilities

                            }

                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
