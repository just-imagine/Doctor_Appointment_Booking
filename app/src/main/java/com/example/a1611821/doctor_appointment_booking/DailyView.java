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
    ScrollView Update;
    User user;
    Dialog bookingDialog;
    String clickedTime;
    ImageView monthTheme;
    ProgressDialog Loading;
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


        monthTheme=(ImageView)findViewById(R.id.dayDisplay);
        mainView=(LinearLayout)findViewById(R.id.mainView);
        //we need the viewed weekday and date from previous intent they were passed
        currentIntent=getIntent();

        //retrieve necessary variables such as username and identity and email to make a new user that can make bookings
        Username=currentIntent.getStringExtra("USERNAME");
        Identity=currentIntent.getStringExtra("IDENTITY");
        Name=currentIntent.getStringExtra("NAME");
        Surname=currentIntent.getStringExtra("SURNAME");
        checkedDate=currentIntent.getStringExtra("checkedDate");

        //create the user
        user=new User();
        user.setEmail(Username);
        user.setIdentity(Identity);
        user.setName(Name);
        user.setSurname(Surname);

        //hoder for cardview of slots
        slotCard=new ArrayList<>();
        //the day object which extends the month object
        thisDay=new Day(this);
        thisDay.setDate(thisDay.toDate(checkedDate));
        thisDay.setCheckedDate(checkedDate);

        //create the time slots for the day
        thisDay.expandSlots();

        //populates the view with textviews representing time slots
        addSlots(thisDay);


        thisDay.setUser(user);
        thisDay.DailySchedule();

        Loading=new ProgressDialog(this);

        thisDay.setLoading(Loading);

        //make a scrollview which will update daily bookings
        Update=(ScrollView)findViewById(R.id.Update);

        Update.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                thisDay.DailySchedule();
            }
        });


        //set the dialog for making and cancelling bookings
        bookingDialog=new Dialog(this);
        bookingDialog.setContentView(R.layout.dialog_booking);

        thisDay.setBookingDialog(bookingDialog);

        //set title
        setTitle(thisDay.getMonth()+" "+thisDay.getYear());

        //set date on on circular icon
        TextView weekday=(TextView)findViewById(R.id.weekDay);

        weekday.setText(thisDay.getWeekDay().substring(0,3)+"\n"+thisDay.getDay());


        //set picture
        setTheme();

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


    //update ui and add the time slots
    public void addSlots(Day d){
        ArrayList<LinearLayout>cardSlots=d.getSlotCards();
        for(int i=0;i<cardSlots.size();++i){
            LinearLayout c=cardSlots.get(i);
            mainView.addView(c);

        }

    }

    //initialy just retrieve slots and put them in the arraylist
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)


    //just displays a popup when clicking a slot and assigns clicked time to that slots time show relevent popup depending
    //on whether slot is booked or free
    public void PopUp(View v){

        TextView cancelSlot=(TextView)bookingDialog.findViewById(R.id.cancel);
        TextView bookSlot=(TextView)bookingDialog.findViewById(R.id.bookslot) ;
        //the event detail textview should be given relevent information
        TextView timeDetails=(TextView)bookingDialog.findViewById(R.id.timedetails);
        clickedTime=((TextView) v).getHint().toString();

        cancelSlot.setVisibility(View.VISIBLE);
        bookSlot.setVisibility(View.VISIBLE);

        int value=Integer.parseInt(clickedTime.substring(3,5))+15;
        String end=clickedTime.substring(0,3)+""+value;
        if(value==60){
            int l=Integer.parseInt(clickedTime.substring(0,2))+1;
            end=""+l+":00";
        }
        timeDetails.setText(thisDay.getWeekDay()+" , "+thisDay.getDay()+" "+thisDay.getMonth()+" "+thisDay.getYear()+"\n\nDuration "+clickedTime+"-"+end);


        if(((TextView) v).getText().toString().equals("Free")){
            cancelSlot.setVisibility(View.INVISIBLE);
            bookingDialog.show();
        }

        else if(((TextView) v).getText().toString().equals("Appointment")){
            bookSlot.setVisibility(View.INVISIBLE);
            bookingDialog.show();
        }
    }

    //just makes a booking aobject and invokes bookslot in day class
    public void BookSlot(final View v){
        Booking b=new Booking(thisDay.getCheckedDate(),clickedTime,user.getIdentity());
        b.setCurrentUser(user.getIdentity());
        thisDay.bookSlot(b,mainView);

    }

    //makes a booking a object that will removed and invokes cancel booking in day class
    public void CancelSlot(final View v) {
        Booking b=new Booking(thisDay.getCheckedDate(),clickedTime,user.getIdentity());
        b.setCurrentUser(user.getIdentity());
        thisDay.cancelBooking(b,mainView);
    }

    public void setTheme(){
        String Month=thisDay.getMonth();
        if(Month.equals("April")){
            monthTheme.setImageResource(R.drawable.april);
        }
        else if(Month.equals("March")){
            monthTheme.setImageResource(R.drawable.march);
        }
        else if(Month.equals("January")){
            monthTheme.setImageResource(R.drawable.january);
        }
        else if(Month.equals("February")){
            monthTheme.setImageResource(R.drawable.february);
        }
        else if(Month.equals("May")){
            monthTheme.setImageResource(R.drawable.may);
        }
        else if(Month.equals("June")){
            monthTheme.setImageResource(R.drawable.june);
        }
        else if(Month.equals("July")){
            monthTheme.setImageResource(R.drawable.july);
        }
        else if(Month.equals("August")){
            monthTheme.setImageResource(R.drawable.august);
        }
        else if(Month.equals("September")){
            monthTheme.setImageResource(R.drawable.september);}
        else if(Month.equals("October")){
            monthTheme.setImageResource(R.drawable.october);}
        else if(Month.equals("November")){
            monthTheme.setImageResource(R.drawable.november);}
        else{
            monthTheme.setImageResource(R.drawable.december);}
    }
}