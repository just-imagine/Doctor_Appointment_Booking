package com.example.a1611821.doctor_appointment_booking;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.res.Resources;

import android.graphics.Color;
<<<<<<< HEAD
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
=======
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
=======
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Viewings extends AppCompatActivity implements  View.OnClickListener{
<<<<<<< HEAD
    CalendarView calendar;
    Date currentDate;
    String formattedDate;
    TextView []timeSlots;
    ArrayList<TextView>clickabletimeSlots;
    ArrayList<Booking>Bookings;
    String highlightDate;
    ScrollView Schedule;
    String globalTime="";
    String username="";
    String Identity="";
    Dialog Book;
    Button BookSlot;
    @RequiresApi(api = Build.VERSION_CODES.M)
=======
    TextView Days[];
    TextView Slots[];
    TextView WeekNumbers;
    TextView Month;
    ImageButton NavigateForwad;
    ImageButton NavigateBackwards;
    int current,nextdate;
    int week,currentWeek;
    int maxdays;
    Dialog diag;
    ArrayList<TextView>ClickableSlots;
    ArrayList<TextView>UnClickableSlots;


    Calendar calendar = Calendar.getInstance();
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewings);
<<<<<<< HEAD
        Bookings=new ArrayList<>();
        //initialize timeslots and calendar
        calendar=(CalendarView)findViewById(R.id.calendar);
        timeSlots=new TextView[11];
        Schedule=(ScrollView)findViewById(R.id.ScrollView);
        //get todays date which will be used in database queries
        currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        formattedDate = df.format(currentDate);
        highlightDate=formattedDate;
        //username from previous activity
        username=getIntent().getStringExtra("Username");
        Identity=getIntent().getStringExtra("Identity");

        //initielize the timeSlots and assignthem
        InitializeSlots();
        AssigntimeSlots(formattedDate);

        clickabletimeSlots=new ArrayList<>(Arrays.asList(timeSlots));

        //set on date change for calendar view
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub
                String information="";
                String day="";
                String Month="";
                month=month+1;
                if(dayOfMonth<10){
                    day="0"+dayOfMonth;
                }

                else {
                    day=day+dayOfMonth;
                }

                if(month<10){
                    Month="0"+month;
                }

                else{
                    Month=""+month;
                }

                information=information+year+Month+day;
                highlightDate=information;
                AssigntimeSlots(information);

            }
        });

        Schedule.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                AssigntimeSlotsOnTouch(highlightDate);
            }
        });
        Book=new Dialog(this);
        Book.setContentView(R.layout.dialog_view);
        BookSlot=(Button)Book.findViewById(R.id.book);
        BookSlot.setOnClickListener(this);

    }

    //assign id to all timeSlots textviews and give them transparent text which is their time
    public  void InitializeSlots(){

        Resources r = getResources();
        String name = getPackageName();
        int t=8;
        for(int i=1;i<=11;++i) {
            timeSlots[i - 1] = (TextView) findViewById(r.getIdentifier("t" + i, "id", name));
            timeSlots[i-1].setTextColor(Color.TRANSPARENT);
            timeSlots[i-1].setOnClickListener(this);
            if(t<10){
                timeSlots[i-1].setText("0"+t+":00");}

            else{
                timeSlots[i-1].setText(t+":00");
            }

            ++t;
        }

    }


    public void AssigntimeSlots(String date){
        //as i assign i dont want the user to use any background information
        int t=8;
        for(int i=0;i<timeSlots.length;++i){
            timeSlots[i].setBackgroundColor(Color.TRANSPARENT);
            timeSlots[i].setTextColor(Color.TRANSPARENT);
            if(t<10){
                timeSlots[i].setText("0"+t+":00");}

            else{
                timeSlots[i].setText(t+":00");
            }

            ++t;
        }
        //put the current date and go get todays time slots
        ContentValues params=new ContentValues();
        params.put("date",date);

        //GET FROM GENERATE BOOKINGS THE SL
        if(Integer.parseInt(highlightDate)-Integer.parseInt(formattedDate)>=0) {
            AsyncHTTPPost getSlots = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/Activities.php", params) {
                @Override
                protected void onPostExecute(String output) {
                    ArrayList<String> databasetimes = new ArrayList<>();
                    ArrayList<Booking> Slots = new ArrayList<>();
                    try {
                        JSONArray results = new JSONArray(output);
                        for (int i = 0; i < results.length(); ++i) {
                            JSONObject obj = results.getJSONObject(i);
                            String Date = obj.getString("DATE");
                            String Time = obj.getString("TIME").substring(0, 5);
                            String Patient = obj.getString("ID_NUMBER");
                            int Validity = obj.getInt("VALIDITY");

                            Booking temp = new Booking(Date, Time, Patient, Validity);
                            temp.setCurrentPatient(username);
                            Slots.add(temp);
                        }

                        Bookings=Slots;

                        for (int i = 0; i < clickabletimeSlots.size(); ++i) {
                            String line = clickabletimeSlots.get(i).getText().toString();
                            for (int j = 0; j < Slots.size(); ++j) {
                                Booking temp = Slots.get(j);
                                if (temp.getTime().equals(line)) {
                                    if (temp.Blocked()) {
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#D81B60"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Unavailable");

                                    } else if (temp.Free()) {
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#16a085"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Available");
                                    } else if (temp.Booked(Identity)) {
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#D81B60"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Unavailable");
                                    }

                                    else if(temp.Myslot(Identity)){
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#FF8300"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Appointment");
                                    }


                                }

                            }


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            getSlots.execute();

        }

        else{
            for (int i = 0; i < timeSlots.length; ++i) {
                timeSlots[i].setTextColor(Color.BLACK);
                timeSlots[i].setText("-----------");
            }

        }
    }

    public void AssigntimeSlotsOnTouch(String date){
        //as i assign i dont want the user to use any background information
        int t=8;

        for(int i=0;i<timeSlots.length;++i){
            timeSlots[i].setHintTextColor(Color.TRANSPARENT);
            if(t<10){

                timeSlots[i].setHint("0"+t+":00");}

            else{
                timeSlots[i].setHint(t+":00");
            }

            ++t;
        }
        //put the current date and go get todays time slots
        ContentValues params=new ContentValues();
        params.put("date",date);

        //GET FROM  BOOKINGS THE SLOTS (FREE,BLOCKED AND BOOKED)
        if(Integer.parseInt(highlightDate)-Integer.parseInt(formattedDate)>=0){
            AsyncHTTPPost getSlots=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/Activities.php",params) {
                @Override
                protected void onPostExecute(String output) {
                    ArrayList<String>databasetimes=new ArrayList<>();
                    ArrayList<Booking>Slots=new ArrayList<>();
                    try {
                        JSONArray results=new JSONArray(output);
                        for(int i=0;i<results.length();++i){
                            JSONObject obj=results.getJSONObject(i);
                            String Date=obj.getString("DATE");
                            String Time=obj.getString("TIME").substring(0,5);
                            String Patient=obj.getString("ID_NUMBER");
                            int Validity=obj.getInt("VALIDITY");

                            Booking temp=new Booking(Date,Time,Patient,Validity);
                            temp.setCurrentPatient(username);
                            Slots.add(temp);
                        }

                        for(int i=0;i<clickabletimeSlots.size();++i){
                            String line=clickabletimeSlots.get(i).getHint().toString();
                            for(int j=0;j<Slots.size();++j){
                                Booking temp=Slots.get(j);


                                if(temp.getTime().equals(line)){
                                    if(temp.Blocked()){
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#D81B60"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Unavailable");

                                    }

                                    else if(temp.Free()){
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#16a085"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Available");
                                    }

                                    else if(temp.Booked(Identity)){
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#D81B60"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Unavailable");
                                    }

                                    else if(temp.Myslot(Identity)){
                                        clickabletimeSlots.get(i).setBackgroundColor(Color.parseColor("#FF8300"));
                                        clickabletimeSlots.get(i).setTextColor(Color.WHITE);
                                        clickabletimeSlots.get(i).setText("Appointment");
                                    }



                                }

                            }



                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            getSlots.execute();}

        else{
            for(int i=0;i<timeSlots.length;++i){
                timeSlots[i].setTextColor(Color.BLACK);
                timeSlots[i].setText("-----------");
            }
        }

    }

    @Override
    public void onClick(View v) {

        if(clickabletimeSlots.contains(v)){
            Resources r = getResources();
            String name = getPackageName();
            String time="",databasetime="";
            if(v.getId()==r.getIdentifier("t" + 1, "id", name)){
                time="08:00";
                databasetime="080000";


            }

            else if(v.getId()==r.getIdentifier("t" + 2, "id", name)){
                time="09:00";
                databasetime="090000";
            }

            else if(v.getId()==r.getIdentifier("t" + 3, "id", name)){
                time="10:00";
                databasetime="100000";
            }


            else if(v.getId()==r.getIdentifier("t" + 4, "id", name)){
                time="11:00";
                databasetime="110000";
            }


            else if(v.getId()==r.getIdentifier("t" + 5, "id", name)){
                time="12:00";
                databasetime="120000";
            }


            else if(v.getId()==r.getIdentifier("t" + 6, "id", name)){
                time="13:00";
                databasetime="130000";
            }

            else if(v.getId()==r.getIdentifier("t" + 7, "id", name)){
                time="14:00";
                databasetime="140000";
            }

            else if(v.getId()==r.getIdentifier("t" + 8, "id", name)){
                time="15:00";
                databasetime="150000";
            }

            else if(v.getId()==r.getIdentifier("t" + 9, "id", name)){
                time="16:00";
                databasetime="160000";
            }

            else if(v.getId()==r.getIdentifier("t" + 10, "id", name)){
                time="17:00";
                databasetime="170000";
            }


            else if(v.getId()==r.getIdentifier("t" + 11, "id", name)){
                time="18:00";
                databasetime="180000";
            }

            //now that we have the clicked textview if its highlighted we should be able
            //to see details of appointment and option to cancel
            globalTime=databasetime;
            TextView clicked=(TextView)v;
            ContentValues params=new ContentValues();
            params.put("date",highlightDate);
            params.put("time",databasetime);
            String instruction=clicked.getText().toString();

            if(instruction.equals("Available")){
                Book.show();
            }




        }

        else if(v.equals(BookSlot)){
            ContentValues Params=new ContentValues();
            Params.put("ID_NUMBER",Identity);
            Params.put("TIME",globalTime);
            Params.put("DATE",highlightDate);

            AsyncHTTPPost booking=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/BookSlot.php",Params) {
                @Override
                protected void onPostExecute(String output) {
                    if(output.equals("success")){
                        Toast.makeText(getApplicationContext(),"Booking successfull",Toast.LENGTH_SHORT).show();
                        AssigntimeSlots(highlightDate);
                        Book.dismiss();
                    }

                }
            };

            booking.execute();

        }

    }
=======
        Month=(TextView)findViewById(R.id.month);
        getMonth();
        InitializeSlots();
        InitializeDays();
        current=calendar.get(Calendar.DAY_OF_MONTH);
        week = calendar.get(Calendar.WEEK_OF_MONTH)+1;
        currentWeek=week;
        nextdate=current;
        NavigateForwad=(ImageButton)findViewById(R.id.NavigateForwad);
        NavigateBackwards=(ImageButton)findViewById(R.id.NavigateBckwards);
        NavigateBackwards.setOnClickListener(this);
        NavigateForwad.setOnClickListener(this);
        UnClickableSlots=new ArrayList<>();

        WeekNumbers=(TextView)findViewById(R.id.WeekNumber);
        WeekNumbers.append(" "+(week));
        int time=8;
        for(int i=0;i<Slots.length;++i){
            Slots[i].setOnClickListener(this);
        }

        ClickableSlots=new ArrayList<TextView>(Arrays.asList(Slots));
        diag = new Dialog(this);
        diag.setContentView(R.layout.dialog_view);

        AdvancedSlots t=new AdvancedSlots(Days,Slots);
        t.Assign();
        BlockSlots();

    }

    public void BlockSlots(){
        String releventDate[]=new String[8];
        int lowerDate=Integer.parseInt(Days[0].getText().toString().split("\\s")[1]);
        int UpperDate=Integer.parseInt(Days[Days.length-1].getText().toString().split("\\s")[1]);
        String dateOne;
        String dateTwo;
        int monthvalue=getMonthValue();

        if(lowerDate<UpperDate){
            if(monthvalue<10){
            dateOne="2019"+"0"+monthvalue+""+lowerDate;
            dateTwo="2019"+"0"+monthvalue+""+UpperDate;}

            else{
                dateOne="2019"+""+monthvalue+""+lowerDate;
                dateTwo="2019"+""+monthvalue+""+UpperDate;
            }
        }

        else{
            if(monthvalue<10){
            dateOne="20190"+""+monthvalue+""+lowerDate;
            dateTwo="20190"+""+(monthvalue+1)+""+UpperDate;}

            else{
                dateOne="2019"+""+monthvalue+""+lowerDate;
                dateTwo="2019"+""+(monthvalue+1)+""+UpperDate;
            }
        }

        ContentValues Params=new ContentValues();
        Params.put("lower",dateOne);
        Params.put("Upper",dateTwo);

        AsyncHTTPPost Blocks=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/Block.php",Params) {
            @Override
            protected void onPostExecute(String output) {
                System.out.println(output);
                ArrayList<String>Indexes=new ArrayList<>();
                try {
                    JSONArray results=new JSONArray(output);
                    for(int i=0;i<results.length();++i){

                        JSONObject obj=results.getJSONObject(i);
                        String date=obj.get("DATE").toString().split("-")[2];
                        String time=obj.get("TIME").toString().split(":")[0];
                        String data="";

                        if(time.charAt(0)=='0'){
                            time=""+time.charAt(1);
                        }

                        data=date+time;
                        Indexes.add(data);

                    }

                    for(int i=0;i<Slots.length;++i){
                        if(Slots[i].getText().toString().contains(",")){
                        System.out.println(Slots[i].getText().toString());
                        String Form=Slots[i].getText().toString().split(",")[1];
                        if(Indexes.contains(Form)){
                            Slots[i].setBackgroundColor(Color.CYAN);
                            Slots[i].setText("Unavailable");
                            UnClickableSlots.add(Slots[i]);
                            ClickableSlots.remove(Slots[i]);
                        }

                        else{
                            Slots[i].setText("Available");
                        }}
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        };
        Blocks.execute();


    }

    public void InitializeDays(){
        Days=new TextView[8];
        Resources r = getResources();
        String name = getPackageName();
        String WeekDays[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        int p =calendar.get(Calendar.DAY_OF_WEEK);
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        for(int i=1;i<=8;++i){
            if(d>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
                d=1;
            }
            Days[i-1]=(TextView)findViewById(r.getIdentifier("M" + i, "id", name));

            if(p==7){
                Days[i-1].setText(WeekDays[p-1] + " "+ d);
                d++;
                p=1;
            }

            else{
                Days[i-1].setText(WeekDays[p-1] + " " + d);
                ++p;
                d++;
            }

        }


    }


    public  void InitializeSlots(){
        Slots=new TextView[88];
        Resources r = getResources();
        String name = getPackageName();
        char Letters[] ={'A','B','C','D','E','F','G','H'};
        int counter=0;
        for(int j=0;j<8;++j){
            for(int i = 1; i <=11; i++) {
                Slots[counter]=(TextView)findViewById(r.getIdentifier(Letters[j]+"" + i, "id", name));
                ++counter;
            }
        }
    }

    public void getMonth(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month = monthName[calendar.get(Calendar.MONTH)];
        Month.setText(month);
    }

    public int getMonthValue(){
        return calendar.get(Calendar.MONTH)+1;
    }



    @Override
    public void onClick(View v) {
        if(v.equals(NavigateForwad)){
            String WeekDays[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
            int p =calendar.get(Calendar.DAY_OF_WEEK);



            if( week<5){
                week++;
                WeekNumbers.setText("Week " + (week));

            nextdate=nextdate+7;
            int temp=nextdate;
            if(nextdate>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
                nextdate=1;
                temp=1;
            }

            for(int i = 1; i <= 8;i++){
                if(temp>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    temp=1;
                }
                if(p==7){
                    Days[i-1].setText(WeekDays[p-1] + " "+ temp);
                    p=1;
                    ++temp;
                }

                else{
                    Days[i-1].setText(WeekDays[p-1] + " " + temp);
                    temp++;
                    ++p;
                }

              /*  AdvancedSlots t=new AdvancedSlots(Days,Slots);
                t.Assign();
                BlockSlots();*/


            }



            }}



    else if(v.equals(NavigateBackwards)){
            String WeekDays[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
            int p =calendar.get(Calendar.DAY_OF_WEEK);

            if(week>calendar.get(Calendar.WEEK_OF_MONTH)+1){
                --week;
                WeekNumbers.setText("Week " + (week));
                nextdate=nextdate-7;
                int temp=nextdate;
                if(nextdate>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    nextdate=1;
                }
                for(int i = 1; i <= 8;i++){
                    if(temp>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
                        temp=1;
                    }
                    if(p==7){
                        Days[i-1].setText(WeekDays[p-1] + " "+ temp);
                        p=1;
                        ++temp;
                    }

                    else{
                        Days[i-1].setText(WeekDays[p-1] + " " + temp);
                        temp++;
                        ++p;
                    }

                    AdvancedSlots t=new AdvancedSlots(Days,Slots);
                    t.Assign();
                    BlockSlots();

                }
            }

            }

    else if(ClickableSlots.contains(v)){
            diag.show();
        }
}
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
}
