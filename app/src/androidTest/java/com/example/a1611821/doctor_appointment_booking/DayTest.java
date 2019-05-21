package com.example.a1611821.doctor_appointment_booking;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DayTest {
    @Mock
    Context myContext=new Application();

    Day myDay;


    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        myDay =new Day(myContext);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setUser(){


    }

    @Test
    public void setBookingDialog() {
    }

    @Test
    public void toDate() {

        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.JANUARY, 9, 0, 0, 0); //Year, month, day of month, hours, minutes and seconds
        Date date = cal.getTime();
        String expected=""+date;
        String result=""+myDay.toDate("20130109");
        assertEquals(expected,result);
        assertNull(myDay.toDate("2013,0109"));
    }

    @Test
    public void findBooking() {
        Booking myBooking = myDay.findBooking("13:30");
        assertNull(myBooking);
        myDay.dailyBookings.add(new Booking("","13:30",""));
        myBooking = myDay.findBooking("13:30");
        assertNotNull(myBooking);
    }

    @Test
    public void getSlotCards() {

    }

    @Test
    public void setTimeSlots() {
    }

    @Test
    public void toTime() {

        String results= myDay.toTime(8,0);
        assertEquals("08:00",results);
        results= myDay.toTime(8,45);
        assertEquals("08:45",results);
        results= myDay.toTime(8,60);
        assertEquals("09:00",results);
        results= myDay.toTime(14,0);
        assertEquals("14:00",results);
        results= myDay.toTime(14,45);
        assertEquals("14:45",results);
        results= myDay.toTime(15,60);
        assertEquals("16:00",results);

    }

    @Test
    public void expandSlots() {
       // assertEquals(0,myDay.slotCards.size());
       // Day yourDay=mock(Day.class);
       // doThrow(yourDay.expandSlots());
       // myDay.expandSlots();
       // assertEquals(41,myDay.slotCards.size());
    }


}