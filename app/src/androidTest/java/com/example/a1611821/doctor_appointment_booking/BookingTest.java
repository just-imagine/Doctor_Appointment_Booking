package com.example.a1611821.doctor_appointment_booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class BookingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        final Field field= temp.getClass().getDeclaredField("Name");
        field.setAccessible(true);
        field.set(temp,"Tshifhiwa");
        final  String results= temp.getName();
        assertEquals("Tshifhiwa",results);
    }

    @Test
    public void getContact() throws NoSuchFieldException, IllegalAccessException {
       final Booking temp= new Booking("","","");
       final Field field= temp.getClass().getDeclaredField("Contact");
       field.setAccessible(true);
       field.set(temp,"0659545378");
       final  String results= temp.getContact();
       assertEquals("0659545378",results);

    }

    @Test
    public void getEmail() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        final Field field= temp.getClass().getDeclaredField("Email");
        field.setAccessible(true);
        field.set(temp,"tmavhona@gmail.com");
        final  String results= temp.getEmail();
        assertEquals("tmavhona@gmail.com",results);
    }

    @Test
    public void getIdentity() {
        final  Booking temp = new Booking("","","9812176232089");
        final String results= temp.getIdentity();
        assertEquals("9812176232089",results);

    }

    @Test
    public void getTime() {
        final Booking temp= new Booking("","12:30","");
        final String results= temp.getTime();
        assertEquals("12:30",results);
    }

    @Test
    public void getDbTime() {
        final Booking temp= new Booking("","12:30","");
        final String results= temp.getDbTime();
        assertEquals("123000",results);

    }

    @Test
    public void getSurname() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        final Field field= temp.getClass().getDeclaredField("Surname");
        field.setAccessible(true);
        field.set(temp,"Mavhona");
        final  String results= temp.getSurname();
        assertEquals("Mavhona",results);

    }

    @Test
    public void getDate() {
        final Booking temp= new Booking("12/12/2029","","");
        final  String results= temp.getDate();
        assertEquals("12/12/2029",results);

    }

    @Test
    public void setCurrentUser() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        temp.setCurrentUser("9812176232089");
        final  Field field =temp.getClass().getDeclaredField("currentUser");
        field.setAccessible(true);
        assertEquals("9812176232089",field.get(temp));

    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        temp.setName("Mavhona");
        final  Field field = temp.getClass().getDeclaredField("Name");
        field.setAccessible(true);
        assertEquals("Mavhona",field.get(temp));

    }

    @Test
    public void setSurname() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        temp.setSurname("Mavhona");
        final  Field field =temp.getClass().getDeclaredField("Surname");
        field.setAccessible(true);
        assertEquals("Mavhona",field.get(temp));
    }

    @Test
    public void setContact() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        temp.setContact("0769545378");
        final  Field field =temp.getClass().getDeclaredField("Contact");
        field.setAccessible(true);
        assertEquals("0769545378",field.get(temp));

    }

    @Test
    public void setEmail() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        temp.setEmail("tmavhona@gmail.com");
        final  Field field =temp.getClass().getDeclaredField("Email");
        field.setAccessible(true);
        assertEquals("tmavhona@gmail.com",field.get(temp));

    }

    @Test
    public void setState() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        temp.setState(0);
        final  Field field =temp.getClass().getDeclaredField("state");
        field.setAccessible(true);
        assertEquals(0,field.get(temp));

    }

    @Test
    public void empty() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        assertEquals(true,temp.Empty());
        final Field field= temp.getClass().getDeclaredField("Identity");
        field.setAccessible(true);
        field.set(temp,"9812176232089");
        assertEquals(false,temp.Empty());


    }

    @Test
    public void blocked() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","9812176232089");
        assertEquals(false,temp.Blocked());
        final Field field= temp.getClass().getDeclaredField("Identity");
        field.setAccessible(true);
        field.set(temp,"Admin");
        assertEquals(true,temp.Blocked());

    }

    @Test
    public void booked() throws NoSuchFieldException, IllegalAccessException {

        final Booking temp= new Booking("","","9812176232089");
        final Field field= temp.getClass().getDeclaredField("currentUser");
        field.setAccessible(true);
        field.set(temp,"9812176236650");
        assertEquals(true,temp.Booked());
        field.setAccessible(true);
        field.set(temp,"9812176232089");
        assertEquals(false,temp.Booked());
    }

    @Test
    public void completed() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","");
        final  Field field =temp.getClass().getDeclaredField("state");
        field.setAccessible(true);
        field.set(temp,1);
        assertEquals(true,temp.Completed());
        field.set(temp,0);
        assertEquals(false,temp.Completed());
    }

    @Test
    public void myBooking() throws NoSuchFieldException, IllegalAccessException {
        final Booking temp= new Booking("","","9812176232089");
        final Field field= temp.getClass().getDeclaredField("currentUser");
        field.setAccessible(true);
        field.set(temp,"9812176232089");
        assertEquals(true,temp.MyBooking());
        field.set(temp,"9812176232088");
        assertEquals(false,temp.MyBooking());

    }

    @Test
    public void equals() throws NoSuchFieldException, IllegalAccessException {

        final Booking temp1= new Booking("12/12/2019","12:30","9812176232089");
        final Booking temp2= new Booking("12/12/2019","12:30","9812176232089");
        assertTrue(temp1.equals(temp2));
        final Field field= temp1.getClass().getDeclaredField("Time");
        field.setAccessible(true);
        field.set(temp1,"22:30");
        assertFalse(temp1.equals(temp2));

    }
}