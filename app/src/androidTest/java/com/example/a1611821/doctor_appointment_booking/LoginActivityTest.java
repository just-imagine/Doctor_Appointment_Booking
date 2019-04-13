package com.example.a1611821.doctor_appointment_booking;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity>mActivityTestRule= new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity mActivity= null;
    Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);
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
    @Test
    public void login(){
        //input login details
        onView(withId(R.id.username)).perform(typeText("tmavhona@gmail.com"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("afterlife"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login)).perform(click());
        Activity HomeActivity =getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(HomeActivity);
        HomeActivity.finish();

    }
    @Test
    public void testForIncorrentUSer(){
        onView(withId(R.id.username)).perform(typeText("tyfvedfhvjhv"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("rfjrfgikgf"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login)).perform(click());
        LoginActivity activity = mActivityTestRule.getActivity();
        onView(withText("Check your username and password")).inRoot(withDecorView(CoreMatchers.not(CoreMatchers.is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
    //@Test
    public void testInternetConnectivity(){
        WifiManager wifi = (WifiManager) mActivity.getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);

        onView(withId(R.id.username)).perform(typeText("tmavhona@gmail.com"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("afterlife"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login)).perform(click());
        LoginActivity activity = mActivityTestRule.getActivity();
        onView(withText("connection error, check your internet connection")).
                inRoot(withDecorView(CoreMatchers.not(CoreMatchers.is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        wifi.setWifiEnabled(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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