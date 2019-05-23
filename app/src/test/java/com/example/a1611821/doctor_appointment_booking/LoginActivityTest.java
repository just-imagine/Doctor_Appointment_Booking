package com.example.a1611821.doctor_appointment_booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {


    LoginActivity Login;

    @Before
    public void setUp() throws Exception {
        Login = Robolectric.setupActivity(LoginActivity.class);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void check() {

        assertNotNull(Login);
    }
    @Test
    public void checkUser() {
        Login.Username.setText("tmavhona@gmail,com");
        Login.findViewById(R.id.login).performClick();
    }
    @Test
    public void checkUser2() {
        Login.Username.setText("tmavhona@gmail,com");
        Login.Password.setText("");
        Login.findViewById(R.id.login).performClick();
    }
    @Test
    public void checkUser3() {
        Login.Username.setText("");
        Login.Password.setText("");
        Login.findViewById(R.id.login).performClick();


    }
}