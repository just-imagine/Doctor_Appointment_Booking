package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;
import android.view.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.Robolectric.buildActivity;

@RunWith(RobolectricTestRunner.class)
public class WelcomeActivity2Test {

    WelcomeActivity2 myReg;

    @Before
    public void setUp() throws Exception {
        myReg = Robolectric.setupActivity(WelcomeActivity2.class);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void check() {

        assertNotNull(myReg);
    }
    @Test
    public void tryToRegister(){
        myReg.Email.setText("tmavhona@gmail.com");
        myReg.Surname.setText("Mavhona");
        myReg.ConfirmPassword.setText("afterlife");
        myReg.Contact.setText("0891111111");
        myReg.Password.setText("afterlife");
        myReg.Name.setText("Tshifhiwa");
        myReg.Identity.setText("9812176232098");
        myReg.findViewById(R.id.female).performClick();
        myReg.findViewById(R.id.createAccount).performClick();


    }
    @Test
    public void onKeyUp_callsOnBackPressedWhichFinishesTheActivity() throws Exception {

        boolean downConsumed =
                myReg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        boolean upConsumed =
                myReg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));

        assertTrue(downConsumed);
        assertTrue(upConsumed);
        assertTrue(myReg.isFinishing());
    }


}