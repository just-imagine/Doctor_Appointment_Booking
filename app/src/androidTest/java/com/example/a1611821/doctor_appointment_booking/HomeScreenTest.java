package com.example.a1611821.doctor_appointment_booking;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeScreenTest {
    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule= new ActivityTestRule<HomeScreen>(HomeScreen.class);

    private HomeScreen myActivity= null;
    @Before
    public void setUp() throws Exception {
        myActivity= mActivityTestRule.getActivity();
    }


    @Test
    public void onCreate() {
    }

    @Test
    public void onBackPressed() {
    }

    @Test
    public void onCreateOptionsMenu() {
    }

    @Test
    public void onOptionsItemSelected() {
    }

    @Test
    public void onNavigationItemSelected() {
    }

    @Test
    public void display() {
    }

    @Test
    public void index() {


        String month ="Apr";
        String expected="April";
        assertEquals(expected,myActivity.Index(month));
        month="May";
        assertEquals("May",myActivity.Index(month));
        month="Jan";
        assertEquals("January",myActivity.Index(month));
        month="Feb";
        assertEquals("February",myActivity.Index(month));
        month="Mar";
        assertEquals("March",myActivity.Index(month));
        month="Jun";
        assertEquals("June",myActivity.Index(month));
        month="Jul";
        assertEquals("July",myActivity.Index(month));
        month="Aug";
        assertEquals("August",myActivity.Index(month));
        month="Sep";
        assertEquals("September",myActivity.Index(month));
        month="Oct";
        assertEquals("October",myActivity.Index(month));
        month="Nov";
        assertEquals("November",myActivity.Index(month));
    }

   // @Test
    public void assignVariables() {
        String date="Tue Apr 16 00:00:00 GMT+02:00 2019";
        int day=16;
        int month =4;
        int year=2019;
        assertEquals("2019-04-16",myActivity.AssignVariables(month,day,year,date));
    }

    @After
    public void tearDown() throws Exception {
    }

}