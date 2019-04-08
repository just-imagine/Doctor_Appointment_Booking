package com.example.a1611821.doctor_appointment_booking;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule= new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity mActivity= null;
    Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(HomeScreen.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorReg= getInstrumentation().addMonitor(WelcomeActivity2.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void testUsernameView(){
        View usernameView=mActivity.findViewById(R.id.username);
        assertNotNull(usernameView);


    }
    @Test
    public void TestUsernameView(){
        View passwordView=mActivity.findViewById(R.id.password);
        assertNotNull(passwordView);

    }

    //@Test
    public void Login(){
        //input login details
        onView(withId(R.id.username)).perform(typeText("nkambule773@gmail.com"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("123456"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login)).perform(click());
        Activity HomeActivit =getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(HomeActivit);
        HomeActivit.finish();

    }
    @Test
    public void RegisterPage(){
        View Reg= mActivity.findViewById(R.id.Registration);
        onView(withId(R.id.Registration)).perform(click());
        Activity WelcomeActivity2 =getInstrumentation().waitForMonitorWithTimeout(monitorReg,5000);
        assertNotNull(WelcomeActivity2);
        WelcomeActivity2.finish();
    }
    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}