package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class HomeScreenTest {

    HomeScreen myHome;

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent(RuntimeEnvironment.application,HomeScreen.class);
        intent.putExtra("USERNAME", "tmavhona");
        intent.putExtra("NAME", "Tshifhiwa");
        intent.putExtra("IDENTITY", "9812176237089");
        intent.putExtra("SURNAME", "Mavhona");
        myHome= Robolectric.buildActivity(HomeScreen.class,intent).setup().get();

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void check(){


        assertNotNull(myHome);
    }

}