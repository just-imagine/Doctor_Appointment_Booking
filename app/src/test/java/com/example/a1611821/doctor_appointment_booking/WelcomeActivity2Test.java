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
public class WelcomeActivity2Test {

    WelcomeActivity2 myReg;
    @Before
    public void setUp() throws Exception {
        myReg= Robolectric.setupActivity(WelcomeActivity2.class);

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void check(){

        assertNotNull(myReg);
    }
}