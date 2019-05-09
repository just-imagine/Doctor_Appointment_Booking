package com.example.a1611821.doctor_appointment_booking;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Day extends Month {

    ArrayList<Booking>dailyBookings;
    ArrayList<TextView>timeSlots;
    ArrayList<LinearLayout>slotCards;
    Context context;

    public Day(Context context){
        super();
        dailyBookings=new ArrayList<>();
        slotCards=new ArrayList<>();
        timeSlots=new ArrayList<TextView>();
        this.context=context;
    }

    //string to date as day will be used once a certain calendar date has been selected
    public Date toDate(String date)  {
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date1;
    }


    //locate a booking at certain time of day
    public Booking findBooking(String time){
        for(int i=0;i<dailyBookings.size();++i){
            Booking t=dailyBookings.get(i);
            if(t.getTime().equals(time)){
                return  t;
            }
        }
        return  null;
    }

    //add to the arraylist that keeps bookings for the day if the booking is not there
    public void addBooking(Booking b){
      if(!dailyBookings.contains(b))
      dailyBookings.add(b);
    }

    //removes a booking from the day ie when we cancel
    public void removeBooking(Booking b){
        dailyBookings.remove(b);
    }

    //set holder

    public void setSlotCards(ArrayList<LinearLayout>s){
        this.slotCards=s;
    }

    public ArrayList<LinearLayout> getSlotCards() {
        return slotCards;
    }

    //set time slots
    public void setTimeSlots(ArrayList<TextView> timeSlots) {
        this.timeSlots = timeSlots;
    }

    // used to obtain time slot labelling of slots
    public String toTime(int hour,int minutes){
        if(hour<10){
            if(minutes<60){
                if(minutes==0)
                    return "0"+hour+":00";

                else{
                    return "0"+hour+":"+minutes;
                }
            }

            else{
                return  toTime(hour+1,0);
            }
        }

        else{
            if(minutes<60){
                if(minutes==0)
                    return hour+":00";

                else{
                    return hour+":"+minutes;
                }
            }

            else{
                return  toTime(hour+1,0);
            }
        }
    }

    //the day object initializes its slots

    public void expandSlots(){
        ArrayList<TextView>slots=new ArrayList<>();
        int starthour=8;
        int duration=0;
        for(int i=0;i<=40;++i){
            String time=toTime(starthour,duration);
            LinearLayout card=new LinearLayout(context);
            card=(LinearLayout) card.inflate(context,R.layout.slot_textview,null);
            TextView timeIndicator=(TextView)card.findViewById(R.id.g);
            TextView slotIndicator=(TextView)card.findViewById(R.id.slot);
            //the slots are given ids
            slotIndicator.setId(100+i);

            //assume all slots are free and colour them green
            slotIndicator.setText("Free");
            if(i<16){
            timeIndicator.setText(time+" AM");}

            else{
                timeIndicator.setText(time+" PM");
            }

            slots.add(slotIndicator);
            slotCards.add(card);

            if(duration==60){
                duration=0;
                starthour++;
            }

            duration+=15;
        }

    }

    //this method will be used to check if data has changed if it has bookings will be updated
    public boolean hasChanged(ArrayList<Booking> sync){
        return dailyBookings.retainAll(sync);
    }

}
