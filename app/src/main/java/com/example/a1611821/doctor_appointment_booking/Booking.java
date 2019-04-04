package com.example.a1611821.doctor_appointment_booking;

public class Booking {
    String date;
    String time;
    String Patient;
    String currentPatient;
    int Validity;

    public Booking(String date, String time, String Patient, int Validity){
        this.date=date;
        this.time=time;
        this.Patient=Patient;
        this.Validity=Validity;
    }

    public void setCurrentPatient(String P){
        this.currentPatient=P;
    }


    boolean Blocked(){
        if(this.Patient.equals("null") && Validity==0){

            return true;
        }

        return  false;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getPatient(){
        return  Patient;
    }

    boolean Booked(String Occupier){
        if(Validity==0 && !Blocked()){
            if(!Occupier.equals(Patient))
            return  true;
        }

        return  false;
    }

    boolean Myslot(String Occupier){
        if(Validity==0 && !Blocked()){
            if(Occupier.equals(Patient)){
                return  true;
            }

            return  false;
        }

        return  false;

    }

    boolean Free(){
        if(Patient.equals("null") && Validity==1){
            return  true;
        }

        return  false;
    }



}
