package com.example.a1611821.doctor_appointment_booking;

import android.app.Dialog;
import android.content.res.Resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Viewings extends AppCompatActivity implements  View.OnClickListener{
    TextView Days[];
    TextView Slots[];
    TextView WeekNumbers;
    ImageButton NavigateForwad;
    int current,nextdate;
    int week;
    Dialog diag;
    ArrayList<TextView>ClickableSlots;

    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewings);

        InitializeSlots();
        InitializeDays();
        current=calendar.get(Calendar.DAY_OF_MONTH);
        week = calendar.get(Calendar.WEEK_OF_MONTH);
        nextdate=current;
        NavigateForwad=(ImageButton)findViewById(R.id.NavigateForwad);
        NavigateForwad.setOnClickListener(this);
        WeekNumbers=(TextView)findViewById(R.id.WeekNumber);
        WeekNumbers.append(" "+calendar.get(Calendar.WEEK_OF_MONTH));
        for(int i=0;i<Slots.length;++i){
            Slots[i].setText("-");
            Slots[i].setOnClickListener(this);
        }

        ClickableSlots=new ArrayList<TextView>(Arrays.asList(Slots));
        diag = new Dialog(this);
        diag.setContentView(R.layout.dialog_view);


    }

    public void InitializeDays(){
        Days=new TextView[8];
        Resources r = getResources();
        String name = getPackageName();
        String WeekDays[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        int p =calendar.get(Calendar.DAY_OF_WEEK);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int counter=1;
        for(int i=1;i<=8;++i){
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
        Slots=new TextView[64];
        Resources r = getResources();
        String name = getPackageName();
        char Letters[] ={'A','B','C','D','E','F','G','H'};
        int counter=0;
        for(int j=0;j<8;++j){
            for(int i = 1; i <=8; i++) {
                Slots[counter]=(TextView)findViewById(r.getIdentifier(Letters[j]+"" + i, "id", name));
                ++counter;
            }
        }
    }



    @Override
    public void onClick(View v) {
        if(v.equals(NavigateForwad)){


            String WeekDays[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
            int p =calendar.get(Calendar.DAY_OF_WEEK);
            week++;
            WeekNumbers.setText("Week " + week);

            if(week>4){
                WeekNumbers.setText("Week " + (week-1));
                NavigateForwad.setClickable(false);
            }
            nextdate=nextdate+7;
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


            }

    }

    else if(ClickableSlots.contains(v)){
            diag.show();
        }
}
}
