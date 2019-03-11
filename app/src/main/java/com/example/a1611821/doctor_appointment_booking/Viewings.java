package com.example.a1611821.doctor_appointment_booking;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.res.Resources;

import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Viewings extends AppCompatActivity implements  View.OnClickListener{
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewings);
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
}
