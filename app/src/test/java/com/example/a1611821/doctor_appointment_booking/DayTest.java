package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;

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
}