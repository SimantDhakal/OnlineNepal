package com.simant.onlinenepal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Donarprofile extends AppCompatActivity {

    TextView name;
    TextView address;
    TextView phone;
    TextView sex;
    TextView blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donarprofile);


        name = findViewById(R.id.explore_name);
        address = findViewById(R.id.explore_address);
        phone = findViewById(R.id.explore_phone);
        sex = findViewById(R.id.explore_sex);
        blood = findViewById(R.id.explore_blood);


        String pname = getIntent().getExtras().getString("name");

        String pphone = getIntent().getExtras().getString("phone");

        String paddress = getIntent().getExtras().getString("address");

        String psex = getIntent().getExtras().getString("sex");

        String pblood = getIntent().getExtras().getString("bloodgroup");


        name.setText(pname);
        address.setText(pphone);
        phone.setText(paddress);
        sex.setText(psex);
        blood.setText(pblood);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sorry for error, We are still under maintainance.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
