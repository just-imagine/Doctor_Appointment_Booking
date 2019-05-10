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



    public Booking(String Date, String Time,String Identity){
        this.Identity=Identity;
        this.Date=Date;
        this.Time=Time;
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

    public String getTime(){
        return  Time;
    }
    //return the time in the db format
    public String getDbTime() {
        String line="";
        String data[]=Time.split(":");

        line=data[0]+data[1]+"00";
        return line;
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

    public void setName(String name){
        Name=Name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setState(int state) {
        this.state = state;
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

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return  true;
        }

        else{
            Booking temp=(Booking)obj;
            if(this.Time.equals(temp.getTime()) && this.Date.equals(temp.getDate())){
                return  true;
            }

            return  false;
        }

    }

   /* public void OccupySlots(ArrayList<TextView>SLots){
        for(int i=0;i< SLots.size();++i){
            TextView Slot=SLots.get(i);
            if(Slot.getHint().equals(Time)){
                String s=Identity;
                String d=currentUser;
                String v=Slot.getText().toString();

                if(this.MyBooking() && !this.Completed()){
                    Slot.setBackgroundColor(Color.parseColor("#4eacc8"));
                    Slot.setText("Appointment");
                    Slot.setTextColor(Color.WHITE);
                }


                else if(this.MyBooking() && this.Completed()){
                    Slot.setBackgroundColor(Color.parseColor("#003366"));
                    Slot.setText("Attended");
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
    }*/


}
