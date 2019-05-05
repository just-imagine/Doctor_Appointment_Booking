package com.example.a1611821.doctor_appointment_booking;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;

public class Booking {


   private String Name;
   private String Surname;
   private String Identity;
   private String Date;
   private String Time;
   private String Contact;
   private String Email;
   private int state;
   private String currentUser;



    public Booking(String Name, String Surname, String Identity,String Contact,String Email, String Date, String Time,int state){
        this.Name=Name;
        this.Surname=Surname;
        this.Identity=Identity;
        this.Contact=Contact;
        this.Email=Email;
        this.Date=Date;
        this.Time=Time;
        this.state=state;

    }


    public String getName(){
        return  Name;
    }

    public  String getContact(){
        return  Contact;
    }

    public  String getEmail(){
        return  Email;
    }

    public String getIdentity() {
        return Identity;
    }

    public String getTime() {
        return Time;
    }

    public String getSurname() {
        return Surname;
    }

    public String getDate() {
        return Date;
    }

    public  void setCurrentUser(String user){
        currentUser=user;
    }


    boolean Empty(){
        if(Identity.equals("")){
            return true;
        }

        else return false;
    }

    boolean Blocked(){
        if(Identity.equals("Admin")){
            return  true;
        }

        return  false;
    }

    boolean Booked(){
        if(!Identity.equals("null") && !Blocked() && !Identity.equals(currentUser)){
            return  true;
        }

        return  false;
    }

    boolean Completed(){
        if(state==1){
            return  true;
        }

        return  false;
    }


    boolean MyBooking(){
        if(currentUser.equals(Identity)){
            return  true;
        }

        return  false;
    }

    public void OccupySlots(ArrayList<TextView>SLots){
        for(int i=0;i< SLots.size();++i){
            TextView Slot=SLots.get(i);
            if(Slot.getHint().equals(Time)){


                if(this.MyBooking()){
                    Slot.setBackgroundColor(Color.parseColor("#4eacc8"));
                    Slot.setText("Appointment");
                    Slot.setTextColor(Color.WHITE);
                }

                else if(this.Blocked()){
                    Slot.setBackgroundColor(Color.parseColor("#d13c04"));
                    Slot.setText("Unavailable");
                    Slot.setTextColor(Color.WHITE);
                }

                else if(this.Empty()){
                    Slot.setBackgroundColor(Color.parseColor("#008577"));
                    Slot.setText("Free");
                    Slot.setTextColor(Color.WHITE);
                }

                else if(this.Booked()){
                    Slot.setBackgroundColor(Color.parseColor("#d13c04"));
                    Slot.setText("Unavailable");
                    Slot.setTextColor(Color.WHITE);
                }





                break;
            }
        }
    }


}
