package com.example.a1611821.doctor_appointment_booking;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;

public class DailyView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Intent currentIntent;
    String Username;
    String Identity;
    String Name;
    String Surname;
    String checkedDate;
    Day thisDay;
    LinearLayout mainView;
    ArrayList<LinearLayout>slotCard;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainView=(LinearLayout)findViewById(R.id.mainView);
        //we need the viewed weekday and date from previous intent they were passed
        currentIntent=getIntent();

        //retrieve necessary variables such as username and identity and password for bookings to be made
        Username=currentIntent.getStringExtra("USERNAME");
        Identity=currentIntent.getStringExtra("IDENTITY");
        Name=currentIntent.getStringExtra("NAME");
        Surname=currentIntent.getStringExtra("SURNAME");
        checkedDate=currentIntent.getStringExtra("checkedDate");

        //hoder for cardview of slots
        slotCard=new ArrayList<>();
        //the day object which extends the month object
        thisDay=new Day(this);
        thisDay.setDate(thisDay.toDate(checkedDate));
        thisDay.setCheckedDate(checkedDate);
        thisDay.expandSlots();

        addSlots(thisDay);




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
        getMenuInflater().inflate(R.menu.daily_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            // Handle the camera action
        } else if (id == R.id.nav_week) {

        } else if (id == R.id.nav_month) {
            finish();

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public ArrayList<TextView>expandSlots(Day d){
        ArrayList<TextView>slots=new ArrayList<>();
        int starthour=8;
        int duration=0;
        for(int i=0;i<=40;++i){
            String time=d.toTime(starthour,duration);
            LinearLayout card=new LinearLayout(this);
            card=(LinearLayout) card.inflate(this,R.layout.slot_textview,null);
            TextView timeIndicator=(TextView)card.findViewById(R.id.g);
            TextView slotIndicator=(TextView)card.findViewById(R.id.slot);
            timeIndicator.setText(time);
            timeIndicator.setHint(time);

            slots.add(slotIndicator);
            slotCard.add(card);

            if(duration==60){
                duration=0;
                starthour++;
            }

            duration+=15;
        }
        return slots;
    }

    //update ui and add the time slots
    public void addSlots(Day d){
        ArrayList<LinearLayout>cardSlots=d.getSlotCards();
        for(int i=0;i<cardSlots.size();++i){
            LinearLayout c=cardSlots.get(i);
            mainView.addView(c);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void DailySchedule(){

    }

    public void PopUp(View V){

    }

    public void BookSlot(final View v){

    }

    public void CancelSlot(final View v) {

    }
}