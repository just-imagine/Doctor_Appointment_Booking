package com.example.a1611821.doctor_appointment_booking;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class DayTest {

    DailyView myDialy;
    Day myDay;
    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent(RuntimeEnvironment.application,DailyView.class);
        intent.putExtra("checkedDate", "20190522");

        myDialy= Robolectric.buildActivity(DailyView.class,intent).setup().get();
        myDay= new Day(myDialy.getApplicationContext());
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void check(){


        assertNotNull(myDialy);
    }
    @Test
    public void expand(){
        assertEquals(0,myDay.timeSlots.size());
        myDay.expandSlots();
        assertEquals(41,myDay.timeSlots.size());

    }
    @Test
    public void bookingUpdateSuccess(){
        LinearLayout mainView= myDialy.findViewById(R.id.mainView);
        String success="success";
        myDay.bookingDialog=new Dialog(myDialy.getApplicationContext());
        myDay.Loading=new ProgressDialog(myDialy.getApplicationContext());
        myDay.bookingUpdate(success,mainView);


    }

    @Test
    public void bookingUpdateFail(){
        LinearLayout mainView= myDialy.findViewById(R.id.mainView);
        String success="fail";
        myDay.bookingDialog=new Dialog(myDialy.getApplicationContext());
        myDay.Loading=new ProgressDialog(myDialy.getApplicationContext());
        myDay.bookingUpdate(success,mainView);
    }

    @Test
    public void CancelUpdateSuccess(){
        LinearLayout mainView= myDialy.findViewById(R.id.mainView);
        String success="success";
        myDay.bookingDialog=new Dialog(myDialy.getApplicationContext());
        myDay.Loading=new ProgressDialog(myDialy.getApplicationContext());
        myDay.cancellationUpdate(success,mainView);


    }

    @Test
    public void CancelUpdateFail(){
        LinearLayout mainView= myDialy.findViewById(R.id.mainView);
        String success="fail";
        myDay.bookingDialog=new Dialog(myDialy.getApplicationContext());
        myDay.Loading=new ProgressDialog(myDialy.getApplicationContext());
        myDay.cancellationUpdate(success,mainView);
    }

    @Test
    public void updateSlotsSameDate(){
        TextView t1=new TextView(myDialy.getApplicationContext());
        TextView t2=new TextView(myDialy.getApplicationContext());
        t1.setHint("12:30");
        t2.setHint("19:45");

        myDay.timeSlots.add(t1);
        myDay.timeSlots.add(t2);
        myDay.setCheckedDate(myDay.getCurrentDate());
        Booking temp=new Booking("20190522","12:30","");
        myDay.dailyBookings.add(temp);

        myDay.updateSlots();

    }


    @Test
    public void updateSlotsPreviousDate(){
        TextView t1=new TextView(myDialy.getApplicationContext());
        TextView t2=new TextView(myDialy.getApplicationContext());
        t1.setHint("12:30");
        t2.setHint("12:45");

        myDay.timeSlots.add(t1);
        myDay.timeSlots.add(t2);
        myDay.setCheckedDate("20190501");
        Booking temp=new Booking("20190522","12:30","");
        myDay.dailyBookings.add(temp);

        myDay.updateSlots();

    }
    @Test
    public void updateSlotsSameDateBookedCompleted(){
        TextView t1=new TextView(myDialy.getApplicationContext());
        TextView t2=new TextView(myDialy.getApplicationContext());
        t1.setHint("12:30");
        t2.setHint("12:45");

        myDay.timeSlots.add(t1);
        myDay.timeSlots.add(t2);
        myDay.setCheckedDate(myDay.getCurrentDate());
        Booking temp=new Booking("20190522","12:30","10");
        temp.setCurrentUser("10");
        temp.setState(1);
        myDay.dailyBookings.add(temp);

        myDay.updateSlots();
    }

    @Test
    public void updateSlotsSameDateBookedNotCompleted(){
        TextView t1=new TextView(myDialy.getApplicationContext());
        TextView t2=new TextView(myDialy.getApplicationContext());
        t1.setHint("12:30");
        t2.setHint("12:45");

        myDay.timeSlots.add(t1);
        myDay.timeSlots.add(t2);
        myDay.setCheckedDate(myDay.getCurrentDate());
        Booking temp=new Booking("20190522","12:30","10");
        temp.setCurrentUser("10");
        temp.setState(0);
        myDay.dailyBookings.add(temp);
        myDay.updateSlots();
    }


}