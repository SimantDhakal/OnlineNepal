package com.simant.onlinenepal;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BCCFragment extends Fragment {


    // String API_KEY = "8190df9eb51445228e397e4185311a66"; // ### YOUE NEWS API HERE ###
    String API_KEY = "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=aa3e019a77f0472189992766e27e3e23";
    String NEWS_SOURCE = "simant";
    ListView listnews;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_SOURCE = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "urlToImage";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";

    private ListView esListView;
    private ProgressBar epProgressBar;
    private View mMainView;

    public BCCFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.fragment_bcc, container, false);


        esListView = mMainView.findViewById(R.id.listnews);
        epProgressBar = mMainView.findViewById(R.id.loader);
        esListView.setEmptyView(loader);

        if(Function.isNetworkAvailable(getActivity()))
        {
            new DownloadNews().execute();
        }else{
            // Toast.makeText(getActivity(), "Please check your internet connection and try again.", Toast.LENGTH_LONG).show();
            existMode();
        }


        if (dataList != null) {
            dataList.clear();
        }

        return mMainView;
    }

    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            epProgressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
        protected String doInBackground(String... args) {
            String xml = "";

            String urlParameters = "";
            xml = Function.excuteGet("https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=aa3e019a77f0472189992766e27e3e23 ", urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            epProgressBar.setVisibility(View.INVISIBLE);

            if(xml.length()>10){ // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_SOURCE, jsonObject.optString(KEY_SOURCE).toString());
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE).toString());
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION).toString());
                        map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                        map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT).toString());
                        dataList.add(map);

                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Sorry, We are under maintainance. It may take an hours.", Toast.LENGTH_SHORT).show();
                }

                ListBBCAdapter adapter = new ListBBCAdapter(getActivity(), dataList);
                esListView.setAdapter(adapter);


                esListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        HashMap<String, String> map = dataList.get(position);
                        Intent i = new Intent(getActivity(), DetailsActivity.class);
                        // i.putExtra("url",map.get(KEY_URL));
                        i.putExtra("url",map.get(KEY_URL));
                        i.putExtra("title",map.get(KEY_TITLE));
                        i.putExtra("source",map.get(KEY_SOURCE));
                        i.putExtra("desc",map.get(KEY_DESCRIPTION));
                        i.putExtra("date",map.get(KEY_PUBLISHEDAT));
                        startActivity(i);
                    }
                });



            }else {
                Toast.makeText(getActivity(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void existMode() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());

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
                                Intent intent = new Intent(getContext(), Solution.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //  finish();
                                getActivity().finish();
                                // Handle onClick event in fragment layout ot exit
                                // handle above offline capabilities

                            }

                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

