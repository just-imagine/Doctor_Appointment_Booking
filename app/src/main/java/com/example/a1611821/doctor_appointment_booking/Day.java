package com.example.a1611821.doctor_appointment_booking;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    User user;
    ProgressDialog Loading;
    Dialog bookingDialog;

    public Day(Context context){
        super();
        dailyBookings=new ArrayList<>();
        slotCards=new ArrayList<>();
        timeSlots=new ArrayList<TextView>();
        this.context=context;
        Loading=new ProgressDialog(context);
    }

    //set the current user
    public void setUser(User User){
        user=User;
    }

    public void setBookingDialog(Dialog dialog){
        bookingDialog=dialog;
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

    //set holder

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


            //the time hint will be used to make a reference when clicked
            slotIndicator.setHint(time);
            slotIndicator.setText("Free");

            if(i<16){
            timeIndicator.setText(time+" AM");}

            else{
                timeIndicator.setText(time+" PM");
            }

            timeSlots.add(slotIndicator);
            slotCards.add(card);

            if(duration==60){
                duration=0;
                starthour++;
            }

            duration+=15;
        }

    }

    //we want to retain any previous bookings we have and add new ones
    public ArrayList<Booking> syncBookings(ArrayList<Booking>sync){

        //return an empty arraylist
        if(sync.size()==0){
            return new ArrayList<Booking>();
        }

        //first retain any bookings that are still there in database
        ArrayList<Booking>temp=new ArrayList<>();
        temp.addAll(dailyBookings);
        //any cancelled or deleted bookings removed
        temp.retainAll(sync);
        //now add new ones by removing common
        sync.removeAll(dailyBookings);

        temp.addAll(sync);

        return  temp;
    }

    //cqueries database for daily schedule for the day
    public void DailySchedule(){
        ContentValues Params=new ContentValues();
        Params.put("DATE",getCheckedDate());
        AsyncHTTPPost getSchedule=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/CSearches.php",Params) {
            @Override
            protected void onPostExecute(String output) {
                ArrayList<Booking>sync=new ArrayList<>();
                try {
                    JSONArray results=new JSONArray(output);
                    for(int i=0;i<results.length();++i){
                        JSONObject obj=results.getJSONObject(i);
                        String Date=obj.getString("DATE");
                        String Time=obj.getString("TIME").substring(0,5);
                        String Identity=obj.getString("ID_NUMBER");
                        Booking B=new Booking(Date,Time,Identity);
                        B.setName(obj.getString("NAME"));
                        B.setSurname(obj.getString("SURNAME"));
                        B.setContact(obj.getString("CONTACT_NO"));
                        B.setEmail(obj.getString("EMAIL_ADDRESS"));
                        B.setCurrentUser(user.getIdentity());
                        B.setState(obj.getInt("STATE"));
                        sync.add(B);

                    }

                    //if the info has changed update ui

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    dailyBookings=syncBookings(sync);
                    updateSlots();

            }
        };

        getSchedule.execute();
    }

    public void updateSlots(){

            Date date=new Date();
            String actualtime=""+date;
            String sub=actualtime.substring(11,16);
            int currenttimevalue=timeValue(sub);
            for(int j=0;j<timeSlots.size();++j){
                TextView slot=timeSlots.get(j);
                String time=slot.getHint().toString();

                Booking b=findBooking(time);

                if(Integer.parseInt(getCheckedDate())-Integer.parseInt(getCurrentDate())>=0){
                if(b!=null){
                    if(b.Booked() || b.Blocked()){
                      slot.setBackgroundColor(Color.parseColor("#d13c04"));
                      slot.setText("Unavailable");}

                     else if(b.MyBooking() && !b.Completed()){
                        slot.setBackgroundColor(Color.parseColor("#4eacc8"));
                        slot.setText("Appointment");

                    }

                    else  if(b.MyBooking() && b.Completed()){
                        slot.setBackgroundColor(Color.parseColor("#003366"));
                        slot.setText("Attended");
                    }
                }

                else{
                    if(timeValue(slot.getHint().toString())>=currenttimevalue && getCheckedDate().equals(getCurrentDate())){
                    slot.setBackgroundColor(Color.parseColor("#008577"));
                    slot.setText("Free");}

                    else if(timeValue(slot.getHint().toString())<currenttimevalue && getCheckedDate().equals(getCurrentDate())){
                        slot.setBackgroundColor(Color.parseColor("#d13c04"));
                        slot.setText("Unavailable");
                    }

                    else{
                        slot.setBackgroundColor(Color.parseColor("#008577"));
                        slot.setText("Free");
                    }
                }
            }
            else{
                    slot.setBackgroundColor(Color.parseColor("#d13c04"));
                    slot.setText("Unavailable");
                }

            }

    }

    //for making a new booking
    public void bookSlot(final Booking b, final LinearLayout mainView){
        //if the user does not have a booking for today they should be able to make a booking
            ContentValues Params=new ContentValues();
            Params.put("DATE",getCheckedDate());
            Params.put("TIME",b.getDbTime());
            Params.put("ID_NUMBER",b.getIdentity());

            AsyncHTTPPost book=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/ConsulationBooking.php",Params) {
                @Override
                protected void onPostExecute(String output) {
                    bookingUpdate(output,mainView);
                    DailySchedule();
                }
            };
            book.execute();

            Loading = ProgressDialog.show(context, "",
                    "Loading. Please wait...", true);

    }

    public void cancelBooking(final Booking b, final LinearLayout mainView){

            ContentValues Params=new ContentValues();
            Params.put("DATE",getCheckedDate());
            Params.put("TIME",b.getDbTime());
            Params.put("ID_NUMBER",b.getIdentity());

            AsyncHTTPPost cancel=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/ConsultationCancel.php",Params) {
                @Override
                protected void onPostExecute(String output) {
                    cancellationUpdate(output,mainView);
                    DailySchedule();

                }
            };
            cancel.execute();

            Loading = ProgressDialog.show(context, "",
                    "Loading. Please wait...", true);

    }

    //updaets  ui accordingly depending on async result
    public void bookingUpdate(String output,LinearLayout mainView){
        if(output.equals("success")){
            bookingDialog.dismiss();
            Loading.dismiss();

            Snackbar success= Snackbar.make(mainView, "Booking successful", Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            View snackBarView = success.getView();
            TextView message = (TextView)snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            message.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            message.setTextSize(17);
            success.show();
        }

        else{
            bookingDialog.dismiss();
            Loading.dismiss();
            Snackbar error=Snackbar.make(mainView, "Booking unsuccessful", Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            View snackBarView = error.getView();
            snackBarView.setBackgroundColor(Color.RED);
            TextView message = (TextView)snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            message.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            message.setTextSize(17);
            error.show();
        }

    }

    public void cancellationUpdate(String output,LinearLayout mainView){
        if(output.equals("success")){
            bookingDialog.dismiss();
            Loading.dismiss();

            Snackbar success= Snackbar.make(mainView, "Appointment cancelled", Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            View snackBarView = success.getView();
            TextView message = (TextView)snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            message.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            message.setTextSize(17);
            success.show();
        }

        else{
            bookingDialog.dismiss();
            Loading.dismiss();
            Snackbar error=Snackbar.make(mainView, "Failed to cancel appointment", Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            View snackBarView = error.getView();
            snackBarView.setBackgroundColor(Color.RED);
            TextView message = (TextView)snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            message.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            message.setTextSize(17);
            error.show();
        }
    }

    public int timeValue(String time){
        String subs[]=time.split(":");
        String val=""+subs[0]+subs[1];
        return  Integer.parseInt(val);
    }

}
