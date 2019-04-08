package com.example.vewmetui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private boolean notLoggedIn = false;// Change this variable to change log in stance
    TextView chat_slideup,online_slideup;
    ArrayList<String> list;
    ArrayList<String> listb;
    BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        chat_slideup = findViewById(R.id.chat_slideup);
        online_slideup = findViewById(R.id.online_slideup);
        //Check Log In Status
            checkLogInStatus(notLoggedIn);
        //Check Log In Status
        list = new ArrayList<>();
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");
        list.add("NEW MESSAGE");

        listb = new ArrayList<>();
        listb.add("USER ONLINE");
        listb.add("USER ONLINE");
        listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");
        listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");
        listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");listb.add("USER ONLINE");



        chat_slideup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(R.layout.upslide_fragmenta);
                BottomSheetListView listView = dialog.findViewById(R.id.listview);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication(),android.R.layout.simple_selectable_list_item,list);
                dialog.show();
                listView.setAdapter(arrayAdapter);
            }
        });

        chat_slideup.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            public void onSwipeTop() {

                dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(R.layout.upslide_fragmenta);
                BottomSheetListView listView = dialog.findViewById(R.id.listview);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication(),android.R.layout.simple_selectable_list_item,list);
                dialog.show();
                listView.setAdapter(arrayAdapter);


            }
        });

        online_slideup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(R.layout.upslide_fragmenta);
                BottomSheetListView listView = dialog.findViewById(R.id.listview);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication(),android.R.layout.simple_selectable_list_item,listb);
                dialog.show();
                listView.setAdapter(arrayAdapter);
            }
        });
        online_slideup.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){

            public void onSwipeTop() {
                dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(R.layout.upslide_fragmenta);
                BottomSheetListView listView = dialog.findViewById(R.id.listview);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication(),android.R.layout.simple_selectable_list_item,listb);
                dialog.show();
                listView.setAdapter(arrayAdapter);            }
        });

      /*  View slideView = findViewById(R.id.upslide_frame);
        slideUp = new SlideUpBuilder(slideView)
                .withStartState(SlideUp.State.HIDDEN)
                .withStartGravity(Gravity.BOTTOM)

                //.withSlideFromOtherView(anotherView)
                //.withGesturesEnabled()
                //.withHideSoftInputWhenDisplayed()
                //.withInterpolator()
                //.withAutoSlideDuration()
                //.withLoggingEnabled()
                //.withTouchableAreaPx()
                //.withTouchableAreaDp()
                //.withListeners()
                //.withSavedState()
                .build();*/














    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.action_sign_out) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_post) {
            // Handle the New Post action
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkLogInStatus(boolean notLoggedIn){
        // check log in status
        if(notLoggedIn){
            // If Not Logged In
            Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signOut(){
        // Specify signOut Code


    }


    public void openFragment(View v) {
        Fragment fragment = new UpslideFragmentA();
        fragment.setEnterTransition(android.R.transition.slide_bottom);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }


}
