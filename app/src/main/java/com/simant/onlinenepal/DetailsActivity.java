package com.simant.onlinenepal;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView titlex;
    TextView sorc;
    TextView descr;
    TextView sdate;
    ImageView imoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titlex = findViewById(R.id.gettext);
        sorc = findViewById(R.id.srcs);
        descr = findViewById(R.id.getdetails);
        imoi = findViewById(R.id.hulp);
        sdate = findViewById(R.id.date);


        String Title = getIntent().getExtras().getString("title");

        String URL = getIntent().getExtras().getString("url");

        String desr = getIntent().getExtras().getString("source");

        String date = getIntent().getExtras().getString("date");

        String desc = getIntent().getExtras().getString("desc");
        titlex.setText(Title);

        Picasso.with(getApplicationContext()).load(URL).into(imoi);

        sorc.setText(desr);
        descr.setText(desc);
        sdate.setText(date);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sorry, We are under maintainance.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
