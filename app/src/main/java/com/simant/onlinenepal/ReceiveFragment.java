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
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveFragment extends Fragment {

    String API_KEY = "https://brainaigence.000webhostapp.com/donateblood.php";
    String NEWS_SOURCE = "simant";
    ListView listnews2;
    ProgressBar loader;

    // SearchView searchView;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_NAME = "name";
    static final String KEY_PHONE = "phone";
    static final String KEY_ADDRESS = "address";
    static final String KEY_SEX = "sex";
    static final String KEY_BLOODGROUP = "bloodgroup";

    // Retrevial sec;

    private ListView esListView2;
    private ProgressBar epProgressBar;
    private View mMainView;

    // private SearchView searchView;


    public ReceiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.fragment_receive, container, false);

        esListView2 = mMainView.findViewById(R.id.listnews2);
        epProgressBar = mMainView.findViewById(R.id.loader);
        esListView2.setEmptyView(loader);

        // searchView = (SearchView) mMainView.findViewById(R.id.idsearch);
        // searchView.setOnQueryTextListener(getActivity(), this);

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
            xml = Function.excuteGet("https://brainaigence.000webhostapp.com/donateblood.php", urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            epProgressBar.setVisibility(View.INVISIBLE);

            if(xml.length()>10){ // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("donateblood");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_NAME, jsonObject.optString(KEY_NAME).toString());
                        map.put(KEY_PHONE, jsonObject.optString(KEY_PHONE).toString());
                        map.put(KEY_ADDRESS, jsonObject.optString(KEY_ADDRESS).toString());
                        map.put(KEY_SEX, jsonObject.optString(KEY_SEX).toString());
                        map.put(KEY_BLOODGROUP, jsonObject.optString(KEY_BLOODGROUP).toString());
                        dataList.add(map);

                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Sorry, We are under maintainance. It may take an hours.", Toast.LENGTH_SHORT).show();
                }


                ListHelpAdapter adapter = new ListHelpAdapter(getActivity(), dataList);
                esListView2.setAdapter(adapter);

                esListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        HashMap<String, String> map = dataList.get(position);
                        Intent i = new Intent(getActivity(), Donarprofile.class);
                        // i.putExtra("url",map.get(KEY_URL));
                        i.putExtra("name",map.get(KEY_NAME));
                        i.putExtra("phone",map.get(KEY_PHONE));
                        i.putExtra("address",map.get(KEY_ADDRESS));
                        i.putExtra("sex",map.get(KEY_SEX));
                        i.putExtra("bloodgroup",map.get(KEY_BLOODGROUP));
                        startActivity(i);
                    }
                });


            }else{
                Toast.makeText(getActivity(), "No news has been updated. Sorry, we will be back soon within an hour.", Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(getContext(), ProfileActivity.class);
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
