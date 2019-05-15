package com.example.a1611821.doctor_appointment_booking;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Month {

    private  String currentDate,checkedDate;
    private String  WeekDay;
    private Date date;

    public Month(){
        this.date= Calendar.getInstance().getTime();
        this.currentDate=databaseFormat(date);
        this.checkedDate=currentDate;
        this.WeekDay=getWeekDay();
    }
    //formats the date accordingly to put  it in database format
    String databaseFormat(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public String getDay(){
        return checkedDate.substring(6,8);
    }

    public String getYear(){
        return checkedDate.substring(0,4);
    }

    public String getMonth(){
        int i=Integer.parseInt(checkedDate.substring(4,6));
        return  getMonthName(i);
    }

    public void  setCheckedDate(String newMonthDate){
        checkedDate=newMonthDate;
    }

    public void setDate(Date d){
        this.date=d;
    }

    public String getWeekDay(){
        WeekDay=(""+date).substring(0,3);
        return WeekDay;
    }

    public String getCheckedDate(){
        return  checkedDate;
    }

    public String getCurrentDate(){
        return  currentDate;
    }

    public String changeCheckedDate(CalendarDay Day){
        int year=Day.getYear();
        //remember java months run from 1 to 11 hence we  add 1
        int month=Day.getMonth()+1;
        int day=Day.getDay();

        //make the new date using new variable values
        String newCheckedDate=""+year;
        if(month<10){
            newCheckedDate=newCheckedDate+"0"+month;
        }
        else if(month>=10){
            newCheckedDate=newCheckedDate+""+month;
        }
        if(day<10){
            newCheckedDate=newCheckedDate+"0"+day;
        }
        else if(day>=10){
            newCheckedDate=newCheckedDate+day;
        }
        return newCheckedDate;
    }

    //given an index of the month retrieve the full name
    public String getMonthName(int i){
        if(i==1){
            return "January";
        }
        else if(i==2){
            return "February";
        }
        else if(i==3){
            return  "March";
        }
        else if(i==4){
            return  "April";
        }
        else if(i==5){
            return  "May";
        }
        else if(i==6){
            return "June";
        }
        else if(i==7){
            return  "July";
        }
        else if(i==8){
            return  "August";
        }
        else if(i==9){
            return "September";
        }
        else if(i==10){
            return  "October";
        }
        else if(i==11){
            return "November";
        }
        else{
            return  "December";
        }
    }

    //return index of the month to be used in getting its name we have yyymmdd we want substring encoding date
    int getMonthIndex(){
        return  Integer.parseInt(checkedDate.substring(4,6));
    }
}
