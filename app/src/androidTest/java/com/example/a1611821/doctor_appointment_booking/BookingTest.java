package com.example.a1611821.doctor_appointment_booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookingTest {
    Booking temp;
    @Before
    public void setUp() throws Exception {
        temp=  new Booking("Tshifhiwa","Mavhona","","0659545378","tmavhona@gmail.com","","",0);

    }

    @Test
    public void getName() {
        final String actual =temp.getName();
        final String expected ="Tshifhiwa";
        assertEquals(expected,actual);
    }

    @Test
    public void getContact() {
        final String actual =temp.getContact();
        final String expected ="0659545378";
        assertEquals(expected,actual);

    }

    @Test
    public void getEmail() {
        final String actual =temp.getEmail();
        final String expected ="tmavhona@gmail.com";
        assertEquals(expected,actual);
    }

    @Test
    public void getIdentity() {
    }

    @Test
    public void getTime() {

    }

    @Test
    public void getSurname() {
        final String actual =temp.getSurname();
        final String expected ="Mavhona";
        assertEquals(expected,actual);
    }

    @Test
    public void getDate() {
    }

    @Test
    public void empty() {
    }

    @Test
    public void blocked() {
    }

    @Test
    public void booked() {
    }

    @Test
    public void completed() {
    }

    @Test
    public void occupySlots() {
    }
    @After
    public void tearDown() throws Exception {
    }
}
