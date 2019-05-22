package com.example.a1611821.doctor_appointment_booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
@Config(constants =BuildConfig.class, sdk = 21)
public class DayTest {

    LoginActivity myDialy;
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void check(){
        myDialy= Robolectric.setupActivity(LoginActivity.class);
        assertNotNull(myDialy);
    }
}