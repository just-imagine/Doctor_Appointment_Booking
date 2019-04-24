package com.example.a1611821.doctor_appointment_booking;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/* the Weekview for the doctor is to display appointments in a week , the week will be a week that includes the selected day from the
 month view , the days are from monday to sunday */

public class WeekView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /*We have two horizontal scrollviews so that we can hide the one that has Weekdays and show the one
    with fixed weekdays their horizontal scroll should move the same */


    /* a list of of global variables that will need access to everywhere this includes our scrollviews the current date
    the checked date the current month. the schedulewhich has bookings
     */
    HorizontalScrollView TopHorizontal;
    HorizontalScrollView BottomHorizontal;
    TableRow TopWeekdays;
    TableRow BottomWeekdays;
    String CurrentMonth;
    String CurrenDate,CheckedDate;
    Intent currentIntent;
    MaterialCalendarView DropDownCalendar;
    TextView WeekDays[];
    TextView MaskWeekdays[];
    ArrayList<Booking>Schedule;
    LinearLayout WeekEvents[];

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
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

        Schedule=new ArrayList<>();
        /*
            We assign our two scrollviews the bottom one will always be invisible and the top one be visible
            when the bottom one scrolls the top one should scroll in the same  way
            this is done by setting onscroll listeners and moving one scroll view to position of the other
         */
        TopHorizontal=(HorizontalScrollView)findViewById(R.id.Tophorizontal);
        BottomHorizontal=(HorizontalScrollView)findViewById(R.id.horizontalScroll);
        TopWeekdays=(TableRow)findViewById(R.id.TopWeekdays);
        BottomWeekdays=(TableRow)findViewById(R.id.WeekDays);

        TopHorizontal.scrollTo(BottomHorizontal.getScrollX(),BottomHorizontal.getScrollY());
        BottomHorizontal.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                TopHorizontal.scrollTo(scrollX,scrollY);
            }
        });


        TopHorizontal.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                BottomHorizontal.scrollTo(scrollX,scrollY);
            }
        });
        //get the current intent so we can get the extra information that was passed from the previous intent
        currentIntent=getIntent();
        CurrentMonth=currentIntent.getStringExtra("Month");
        CurrenDate=currentIntent.getStringExtra("CurrentDate");
        CheckedDate=currentIntent.getStringExtra("CheckedDate");


        //assign thed ropdown calendar which will allow the user to change weeks within the weekiview
        DropDownCalendar=(MaterialCalendarView)findViewById(R.id.DropDownCalendar);
        String formateddate=CheckedDate.substring(0,4)+"/"+CheckedDate.substring(4,6)+"/"+CheckedDate.substring(6,8);
        DropDownCalendar.setDateSelected(Calendar.getInstance().getTime(), true);
        DropDownCalendar.setCurrentDate(ToDate(CheckedDate));
        DropDownCalendar.setDateSelected(ToDate(formateddate), true);
        DropDownCalendar.setCurrentDate(Calendar.getInstance());
        DropDownCalendar.setSelectionColor(Color.RED);
        DropDownCalendar.addDecorator(new CurrentDateDecorator(this));

        //set the onDatechange listener which should get the week the user has selected to view

        DropDownCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                int year=calendarDay.getYear();
                int month=calendarDay.getMonth()+1;
                int day=calendarDay.getDay();
                String date=""+calendarDay.getDate();

                CheckedDate=AssignVariables(month,day,year,date);
                //set the new dates then add events
                setDays();
                AddEvents();

            }
        });


        //the mask weekdays are the hidden ones but we scroll using them
        MaskWeekdays=new TextView[7];

        //the top weekdays will be assigned relevant dates
        WeekDays=Weekdays();

        //assign corresponding dates to weekdays from Mon - Sun
        setDays();

        //set the title of the intent to the current month and year


        setTitle(getMonth(CurrentMonth)+" "+CheckedDate.substring(0,4));
        toolbar.setTitleTextColor(Color.BLACK);
        // Weekevents is an array of linear layouts each day has hours that have linear layouts to fill in hours of the day
        // Events initialises the array and Addevents  populates the linear layouts
        WeekEvents=Events();
        //populate textviews
        AddEvents();

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
        getMenuInflater().inflate(R.menu.week_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.item1){
            //this shows and hides the dropdown calendar
            ShowCalendar();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_month) {
            finish();
        } else if (id == R.id.nav_progress) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        else if(id==R.id.nav_day){




        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*The show calendar method is used to show / hide the dropdown calendar for changing dates
     */
    public  void ShowCalendar(){
        TextView ChangeCalendar=(TextView)findViewById(R.id.ChangeCalendar);
        if(DropDownCalendar.getLayoutParams().height==0){
            DropDownCalendar.setLayoutParams(TopWeekdays.getLayoutParams());
        }
        else{
            DropDownCalendar.setLayoutParams(ChangeCalendar.getLayoutParams());
        }
    }

    //get month generates the full name of a month given a substring of the month
    public  String getMonth(String x){

        if(x.contains("Apr")){
            return  "April";
        }
        else if(x.contains("Mar")){
            return  "March";
        }
        else if(x.contains("Jan")){
            return  "January";
        }
        else if(x.contains("Feb")){
            return  "February";
        }
        else if(x.contains("May")){
            return  "May";
        }
        else if(x.contains("Jun")){
            return "June";
        }
        else if(x.contains("Jul")){
            return  "July";
        }
        else if(x.contains("Aug")){
            return  "August";
        }
        else if(x.contains("Sep")){
            return  "September";}
        else if(x.contains("Oct")){
            return  "October";}
        else if(x.contains("Nov")){
            return  "November";}
        else{
            return  "December";}
    }

    //Events initialises the array of linear layouts and returns an array with all layouts
    public  LinearLayout[] Events(){
        Resources r = getResources();
        String name = getPackageName();
        String EventNames[]={"M","T","W","TH","FR","SA","SU"};
        LinearLayout Events[]=new LinearLayout[77];
        int  value=0;
        for(int i=0;i<7;++i){
            String column=EventNames[i];
            for(int j=8;j<=18;++j){
                LinearLayout temp=(LinearLayout)findViewById(r.getIdentifier(column+""+j,"id",name));
                Events[value]=temp;
                ++value;
            }

        }
        return Events;
    }


    //Weekdays initialises the array of textviews that have weekdays
    public TextView[] Weekdays(){
        Resources r = getResources();
        String name = getPackageName();
        String WeekDays[]={"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        String DummyWeekDays[]={"F1","F2","F3","F4","F5","F6","F7"};
        TextView Weekdays[]=new TextView[7];
        DropDownCalendar.setCurrentDate(Calendar.getInstance());
        for(int i=0;i<WeekDays.length;++i) {
            TextView weekday,dummyWeekday;
            weekday = (TextView) findViewById(r.getIdentifier(WeekDays[i], "id", name));
            dummyWeekday=(TextView)findViewById(r.getIdentifier(DummyWeekDays[i],"id",name));
            Weekdays[i]=weekday;
            dummyWeekday.setTextColor(Color.TRANSPARENT);
            dummyWeekday.setHintTextColor(Color.TRANSPARENT);
            MaskWeekdays[i]=dummyWeekday;;

        }
        return  Weekdays;
    }

    /*AddEvents queries the databas e for the week and retrieves all relevant information for the week
    it calls Populatedays which the calls populatehours
     */

    public void AddEvents(){
        //clear the array so that new data is added in it
        Schedule.clear();
        String line1=WeekDays[0].getText().toString();
        String line2=WeekDays[WeekDays.length-1].getText().toString();
        String Order[]=DateOrder(line1.split("\n")[1],line2.split("\n")[1]);
        ContentValues Params=new ContentValues();
        Params.put("LDATE",Order[0]);
        Params.put("UDATE",Order[1]);
        final AsyncHTTPPost WeekSchedule=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/ConsultationWeek.php",Params) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray results = new JSONArray(output);
                    for (int i = 0; i < results.length(); ++i) {
                        JSONObject obj = results.getJSONObject(i);
                        String Name=obj.getString("NAME");
                        String Surname = obj.getString("SURNAME");
                        String Identity = obj.getString("ID_NUMBER");
                        String Email = obj.getString("EMAIL_ADDRESS");
                        String Contact = obj.getString("CONTACT_NO");
                        String Date = obj.getString("DATE");
                        String Time = obj.getString("TIME").substring(0, 5);
                        int State = obj.getInt("STATE");
                        Booking temp = new Booking(Name, Surname, Identity, Contact, Email, Date, Time, State);
                        temp.setCurrentUser(getIntent().getStringExtra("Identity"));
                        Schedule.add(temp);
                    }
                    PopulateDays(WeekDays);

                } catch (JSONException e) {
                    PopulateDays(WeekDays);
                    e.printStackTrace();
                }

            }
        };

        WeekSchedule.execute();
    }


    //converts a given string to java date form that can be used by calendar
    public Date ToDate(String line){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date d = sdf.parse(line);
            return  d;
        } catch (ParseException ex) {

            Log.v("Exception", ex.getLocalizedMessage());
            return  null;
        }
    }

    /*Offset is used to compute the dates that should be in the depending on the selected day
     i.e if selected day is wednes day then ofset is 2 we are 2 days away from Monday this value is used by set days
     mthod
     */
    public  int Offset(String line){
        if(line.equals("Mon")){
            return  0;
        }
        else if(line.equals("Tue")){
            return 1;
        }
        else if(line.equals("Wed")){
            return  2;
        }
        else if(line.equals("Thu")){
            return  3;
        }
        else if(line.equals("Fri")){
            return  4;
        }
        else if(line.equals("Sat")){
            return  5;
        }
        else{
            return  6;
        }
    }

    /*uses the return value of the offset function to assign dates to the weekdays i needed to cover leap years abd february as special case
     */
    public void setDays(){

        CalendarDay Day=DropDownCalendar.getSelectedDate();
        Date selectedDate=Day.getDate();
        String LongDate=""+selectedDate;
        String DayOfWeek=LongDate.substring(0,3);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(selectedDate);
        int compare=Integer.parseInt(CurrenDate.substring(6,8).trim());
        int comparemonth=Integer.parseInt(CurrenDate.substring(4,6));
        int startNumber=Integer.parseInt(CheckedDate.substring(6,8).trim())-Offset(DayOfWeek);
        int max=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        GregorianCalendar date= (GregorianCalendar) GregorianCalendar.getInstance();
        date.set(selectedDate.getYear(),1,1);

        boolean Feb=false;
        int priormax=30;

        if(max==30){
            priormax=31;
        }
        else if( max==31){
            priormax=30;
        }

        if(selectedDate.getMonth()==2){
            if(date.isLeapYear(selectedDate.getYear())){
                priormax=29;
            }

            else{
                priormax=28;
            }
            Feb=true;
        }
        for(int i=0;i<WeekDays.length;++i){
            String line=WeekDays[i].getText().toString().substring(0,3);

            if(startNumber<=max){
                if(startNumber==compare && comparemonth==Day.getMonth()+1){
                    WeekDays[i].setTextColor(Color.BLUE);
                }
                else{
                    WeekDays[i].setTextColor(Color.BLACK);
                }
                if(startNumber<=0){
                    int val=startNumber+priormax;
                    if(Feb){
                        if(val==priormax){
                            startNumber=max;
                        }
                    }

                    WeekDays[i].setText(line+"\n"+val);}

                else{
                    WeekDays[i].setText(line+"\n"+startNumber);
                }

            }
            else{
                startNumber=1;
                WeekDays[i].setText(line+"\n"+startNumber);
            }

            MaskWeekdays[i].setText(line+"\n"+startNumber);
            ++startNumber;
        }
        DropDownCalendar.setDateSelected(Calendar.getInstance().getTime(), true);
    }

    /* Everytime when  date is changed the variable month year and day change theyb must be assigned accordingly to get the new
    checked date
     */
    public  String AssignVariables(int month,int day,int year,String LongDate){
        String g;
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
        CheckedDate=g;
        String MonthOfYear=LongDate.substring(4,8).trim();
        setTitle(getMonth(MonthOfYear)+" "+CheckedDate.substring(0,4));
        return  g;
    }

    /* this method is use to get the range of dates for the week from min - max i.e if we have Mon 2 the sun will be 8
        and range is 1-8 whereas if we have values for monday in which the week ends before sunday we must increment the month
     */
    public  String[] DateOrder(String DateOne,String DateTwo){
        int DateOneValue=Integer.parseInt(DateOne);
        int DateTwoValue=Integer.parseInt(DateTwo);

        if(DateOneValue<DateTwoValue){
            if(DateOneValue<10){
                DateOne=CheckedDate.substring(0,4)+CheckedDate.substring(4,6)+"0"+DateOneValue;}

            else{
                DateOne=CheckedDate.substring(0,4)+CheckedDate.substring(4,6)+""+DateOneValue;
            }
            if(DateTwoValue<10){
                DateTwo=CheckedDate.substring(0,4)+CheckedDate.substring(4,6)+"0"+DateTwoValue;
            }

            else{
                DateTwo=CheckedDate.substring(0,4)+CheckedDate.substring(4,6)+""+DateTwoValue;
            }
            String Dates[]={DateOne,DateTwo};
            return  Dates;
        }

        else{
            int nextmonth=Integer.parseInt(CheckedDate.substring(4,6))+1;
            int year=Integer.parseInt(CheckedDate.substring(0,4));
            if(nextmonth==13){
                year=year+1;
                nextmonth=1;

            }
            String month="";
            if(nextmonth<10){
                month="0"+nextmonth;
            }
            else{
                month=""+nextmonth;
            }

            if(DateOneValue<10){
                DateOne=CheckedDate.substring(0,4)+CheckedDate.substring(4,6)+"0"+DateOneValue;}

            else{
                DateOne=CheckedDate.substring(0,4)+CheckedDate.substring(4,6)+""+DateOneValue;
            }
            if(DateTwoValue<10){
                DateTwo=""+year+""+month+"0"+DateTwoValue;
            }

            else{
                DateTwo=year+""+month+""+DateTwoValue;
            }

            String Dates[]={DateOne,DateTwo};
            return  Dates;
        }

    }
    /* this method is used by add Events to fill in the week schedule a day has several working hours that have different appointments
    hence populateDays calls populate hours which fills in the relevent activities for those hours of the day
     */
    public void PopulateDays(TextView[] Days){
        for(int i=0;i<Days.length;++i){
            TextView day=Days[i];
            String info[]=day.getText().toString().split("\n");
            String line=info[0];
            String line2=info[1];
            PopulateHours(line,line2);
        }

    }

    /* Populate hours is used by Populatedays to fill in activities for different hours of the day */
    public void  PopulateHours(String day,String date){
        for(int i=8;i<=18;++i){
            String Id=Identifier(day)+""+i;
            LinearLayout L=getLinearLayout(Id);
            L.removeAllViews();
            ArrayList<Booking>Hour=getBookings(i,date);
            if(!Hour.isEmpty()){
                for(int j=0;j<Hour.size();++j){
                    Booking temp=Hour.get(j);
                    LinearLayout t=(LinearLayout) View.inflate(this,R.layout.booking_textview,null);
                    if(temp.MyBooking()) {
                        String time = temp.getTime().substring(0, 5);
                        String duration = "";
                        int value = Integer.parseInt(time.substring(3, 5)) + 15;
                        if (value < 60) {
                            duration = time.substring(0, 2) + ":" + value;

                        } else {
                            int nexthour = Integer.parseInt(time.substring(0, 2)) + 1;
                            duration = "" + nexthour + ":00";
                        }
                        TextView a = (TextView) t.findViewById(R.id.hourdivision);
                        a.setText("APPOINTMENT" + "\n" + time + "-" + duration);
                        L.addView(t);
                    }

                    /*else if(temp.Booked()){
                        String time = temp.getTime().substring(1, 5);
                        String duration = "";
                        int value = Integer.parseInt(time.substring(2, 4)) + 15;
                        if (value < 60) {
                            duration = time.substring(0, 2) + "" + value;

                        } else {
                            int nexthour = Integer.parseInt(time.substring(0, 1)) + 1;
                            duration = "" + nexthour + "00";
                        }
                        TextView a = (TextView) t.findViewById(R.id.hourdivision);
                        a.setText("UNAVAILABLE" + "\n" + time + "-" + duration);
                        L.addView(t);
                    }*/
                }
            }

            else{
                LinearLayout t=(LinearLayout) View.inflate(this,R.layout.booking_textview,null);
                TextView a=(TextView)t.findViewById(R.id.hourdivision) ;
                a.setText("APPOINTMENT"+"\n"+"9:15-9:30");
                a.setTextColor(Color.TRANSPARENT);
                a.setVisibility(View.INVISIBLE);
                L.addView(t);}
        }
    }

    /* getBookings maps down to which day and hour of day the activity belongs this is done by using the time and date that the booking has
    which are unique
     */

    public ArrayList<Booking> getBookings(int time,String date){
        ArrayList<Booking>Hours=new ArrayList<>();
        for(int i=0;i<Schedule.size();++i){
            Booking temp=Schedule.get(i);
            String Day=temp.getDate();
            int Datevalue=Integer.parseInt(Day.substring(8,10));
            int Value=Integer.parseInt(date);
            if(time<10) {
                if (temp.getTime().substring(0, 2).equals("0"+time) && Datevalue==Value){
                    Hours.add(temp);
                }
            }
            else{
                if (temp.getTime().substring(0, 2).equals(""+time) && Datevalue==Value){
                    Hours.add(temp);
                }
            }
        }
        return  Hours;
    }
    /* getIdentifier is used by PopulateHours  l as means of converting String values of a day to relevent string that is
    part of id and can be passed to getLinearlayout
     */
    public  String Identifier(String day){
        if(day.equals("Mon")){
            return  "M";
        }
        else if(day.equals("Tue")){
            return  "T";
        }
        else if(day.equals("Wed")){
            return  "W";
        }
        else if(day.equals("Thu")){
            return  "TH";
        }
        else if(day.equals("Fri")){
            return  "FR";
        }
        else if(day.equals("Sat")){
            return  "SA";
        }
        else{
            return  "SU";
        }
    }

    /* retrieves the relevent linear layout corresponding to certain hour of particular day
        makes use of Identifier
     */
    public LinearLayout getLinearLayout(String line){
        Resources r = getResources();
        String name = getPackageName();
        LinearLayout L=(LinearLayout)findViewById(r.getIdentifier(line,"id",name));
        L.removeAllViews();
        return  L;
    }

}