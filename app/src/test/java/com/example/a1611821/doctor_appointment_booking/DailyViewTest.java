package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class DailyViewTest {



    DailyView myDaily;
    Day myDay;
    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent(RuntimeEnvironment.application,DailyView.class);
        intent.putExtra("checkedDate", "20190522");

        myDaily= Robolectric.buildActivity(DailyView.class,intent).setup().get();

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void check(){


        assertNotNull(myDaily);
    }

    @Test
    public void PopUp(){
        TextView T=new TextView(myDaily.getApplicationContext());

        myDaily.clickedTime="08:00";
        T.setHint("08:00");
        T.setText("Free");

        myDaily.PopUp(T);

    }
}