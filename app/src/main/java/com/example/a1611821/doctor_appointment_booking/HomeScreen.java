package com.example.a1611821.doctor_appointment_booking;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    MaterialCalendarView Calendar;
    boolean FirstClick=false;
    int HighlightYear=0,HighlightMonth=0,HighlightDay=0;

    Date currentDate;
    String current_date,checked_date;
    String DayOfWeek;
    String DayOfMonth;
    String Year;
    String MonthOfYear;
    String globalDate;
    String globalDateDay;
    ImageView display;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Date FirstHighlight = java.util.Calendar.getInstance().getTime();
        final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        final String FormattedDate = df.format(FirstHighlight);
        current_date=FormattedDate;
        checked_date=current_date;

        Calendar=(MaterialCalendarView) findViewById(R.id.thing);
        display=(ImageView)findViewById(R.id.display);
        final Calendar calendar = java.util.Calendar.getInstance();
        Calendar.setDateSelected(calendar.getTime(), true);
        Calendar.setSelectionColor(Color.RED);
        Calendar.setCurrentDate(calendar);

        Calendar.addDecorator(new CurrentDateDecorator(this));

        Calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                int day=calendarDay.getDay();
                String Long=""+calendarDay.getDate();
                int month=calendarDay.getMonth()+1;
                int year=calendarDay.getYear();
                checked_date=AssignVariables(month,day,year,Long);
                Calendar.setDateSelected(calendar.getTime(), true);
            }
        });

        Calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
                int day=calendarDay.getDay();
                String Long=""+calendarDay.getDate();
                int month=calendarDay.getMonth()+1;
                int year=calendarDay.getYear();
                Calendar.setDateSelected(calendar.getTime(), true);
                checked_date=AssignVariables(month,day,year,Long);
                Display(Long);
            }
        });

        Calendar.setOnDateLongClickListener(new OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay) {


                int day=calendarDay.getDay();
                String Long=""+calendarDay.getDate();
                int month=calendarDay.getMonth()+1;
                int year=calendarDay.getYear();

                checked_date=AssignVariables(month,day,year,Long);
                Intent DailyView=new Intent(getApplicationContext(),DailyView.class);
                DailyView.putExtra("WeekDay",Long.substring(0,4).trim());
                DailyView.putExtra("Date",""+day);
                DailyView.putExtra("Month",Long.substring(4,8).trim());
                DailyView.putExtra("Current",current_date);
                DailyView.putExtra("Checked",checked_date);
                startActivity(DailyView);
            }
        });
       currentDate=java.util.Calendar.getInstance().getTime();
        globalDate=df.format(currentDate);
        Date date=null;
        try {
             date = df.parse(globalDate);
             globalDateDay=""+date;
             DayOfWeek=globalDateDay.substring(0,4).trim();
             DayOfMonth=current_date.substring(6,8);
            MonthOfYear=globalDateDay.substring(4,8).trim();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Display(globalDateDay);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Calendar.setDate(rebase);
            }
        });
        toolbar.setTitleTextColor(Color.BLACK);
        setTitle(Index(MonthOfYear)+" "+checked_date.substring(0,4));
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
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            // Handle the camera action
        } else if (id == R.id.nav_day) {
            Intent DailyView=new Intent(getApplicationContext(),DailyView.class);
            DailyView.putExtra("WeekDay",DayOfWeek);
            DailyView.putExtra("Date",DayOfMonth);
            DailyView.putExtra("Month",MonthOfYear);
            DailyView.putExtra("Current",current_date);
            DailyView.putExtra("Checked",checked_date);
            startActivity(DailyView);


        } else if (id == R.id.nav_week) {

        } else if (id == R.id.nav_month) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void  Display(String input){

        if(!input.equals("")){
            String  day=input.substring(0,4);
            String Month=input.substring(4,8).trim();

            if(Month.equals("Apr")){
                display.setImageResource(R.drawable.april);
            }

            else if(Month.equals("Mar")){
                display.setImageResource(R.drawable.march);
            }

            else if(Month.equals("Jan")){
                display.setImageResource(R.drawable.january);
            }

            else if(Month.equals("Feb")){
                display.setImageResource(R.drawable.february);
            }

            else if(Month.equals("May")){

                display.setImageResource(R.drawable.may);
            }

            else if(Month.equals("Jun")){
                display.setImageResource(R.drawable.june);
            }

            else if(Month.equals("Jul")){
                display.setImageResource(R.drawable.july);

            }


            else if(Month.equals("Aug")){
                display.setImageResource(R.drawable.august);
            }

            else if(Month.equals("Sep")){
                display.setImageResource(R.drawable.september);}

            else if(Month.equals("Oct")){
                display.setImageResource(R.drawable.october);}

            else if(Month.equals("Nov")){
                    display.setImageResource(R.drawable.november);}

            else{
                display.setImageResource(R.drawable.december);}


            }
    }

    public  String Index(String x){

        if(x.equals("Apr")){
            return  "April";
        }

        else if(x.equals("Mar")){
            return  "March";
        }

        else if(x.equals("Jan")){
            return  "January";
        }

        else if(x.equals("Feb")){
            return  "February";
        }

        else if(x.equals("May")){

            return  "May";
        }

        else if(x.equals("Jun")){
            return "June";
        }

        else if(x.equals("Jul")){
            return  "Jul";

        }

        else if(x.equals("Aug")){
            return  "August";
        }

        else if(x.equals("Sep")){
            return  "September";}

        else if(x.equals("Oct")){
            return  "October";}

        else if(x.equals("Nov")){
            return  "November";}

        else{
            return  "December";}
    }

    public  String AssignVariables(int month,int day,int year,String LongDate){
        String weekday=LongDate.substring(0,3);
        String g="";
        if(month<10){
            if(day<10)
                g=""+year+"0"+month+"0"+day;

            else {
                g=""+year+"0"+month+""+day;
            }
        }


        else{
            if(day<10)
                g=""+year+month+"0"+day;

            else {
                g=""+year+month+""+day;
            }
        }

        checked_date=g;
        DayOfMonth=""+day;
        DayOfWeek=weekday;
        MonthOfYear=LongDate.substring(4,8).trim();
        Display(LongDate);
        setTitle(Index(MonthOfYear)+" "+checked_date.substring(0,4));
        return  g;
    }

}
