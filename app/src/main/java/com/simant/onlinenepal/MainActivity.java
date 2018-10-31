package com.simant.onlinenepal;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button;

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private SportFragment sportFragment;
    private NewsFragment newsFragment;
    private ExploreFragment exploreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        libraryFragment = new LibraryFragment();
        sportFragment = new SportFragment();
        newsFragment = new NewsFragment();
        exploreFragment = new ExploreFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_sport:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(sportFragment);
                        return true;

                    case R.id.nav_news:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(newsFragment);
                        return true;

                    case R.id.nav_liby:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(libraryFragment);
                        return true;

                    case R.id.nav_explore:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(exploreFragment);
                        return true;

                    default:
                        return false;
                }

            }


        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {

            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bbcnews) {

            // Pass to fragment


        } else if (id == R.id.share) {

        } else if (id == R.id.jewellery) {

        } else if (id == R.id.horoscope) {

        } else if (id == R.id.wallpaper) {

        } else if (id == R.id.blood) {

            // Handle the receive action
            // Intent intent = new Intent(getActivity(), ReceiveFragment.class);
            // startActivity(intent);

        } else if (id == R.id.phone) {

            // Passed from Java class to Fragment

            Intent intent = new Intent(MainActivity.this, Directory.class);
            startActivity(intent);

        } else if (id == R.id.profile) {

            // Handle the profile action
            // Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            // startActivity(intent);


        } else if (id == R.id.team) {

            // Hanlde the Team activity
            // Intent intent = new Intent(MainActivity.this, Team.class);
            // startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
