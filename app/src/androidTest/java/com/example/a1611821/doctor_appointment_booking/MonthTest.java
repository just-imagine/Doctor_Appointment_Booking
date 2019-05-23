package com.example.a1611821.doctor_appointment_booking;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class MonthTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getDay() throws NoSuchFieldException, IllegalAccessException{
        final Month temp= new Month();
        final Field field= temp.getClass().getDeclaredField("checkedDate");
        field.setAccessible(true);
        field.set(temp,"20190929");
        final  String results= temp.getDay();
        assertEquals("29",results);
    }
    @Test
    public void getYear() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        final Field field=temp.getClass().getDeclaredField("checkedDate");
        field.setAccessible(true);
        field.set(temp,"20190929");
        final String results=temp.getYear();
        assertEquals("2019",results);
    }
    @Test
    public void getMonth() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        final Field field=temp.getClass().getDeclaredField("checkedDate");
        field.setAccessible(true);
        field.set(temp,"20190929");
        final String results=temp.getMonth();
        assertEquals("September",results);
    }
    @Test
    public void setCheckedDate() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        temp.setCheckedDate("20190929");
        final Field field=temp.getClass().getDeclaredField("checkedDate");
        field.setAccessible(true);
        assertEquals("20190929",field.get(temp));
    }
    @Test
    public void setDate() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        Date date = Calendar.getInstance().getTime();
        temp.setDate(date);
        final Field field=temp.getClass().getDeclaredField("date");
        field.setAccessible(true);
        assertEquals(date,field.get(temp));
    }
    @Test
    public void getWeekDay() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        final Field field=temp.getClass().getDeclaredField("date");
        field.setAccessible(true);
        Date date = Calendar.getInstance().getTime();
        field.set(temp,date);
        final String results=temp.getWeekDay();
        assertEquals((""+date).substring(0,3),results);

    }
    @Test
    public void getCheckedDate() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        final Field field=temp.getClass().getDeclaredField("checkedDate");
        field.setAccessible(true);
        field.set(temp,"20190929");
        final String results=temp.getCheckedDate();
        assertEquals("20190929",results);
    }
    @Test
    public void getCurrentDate() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
        final Field field=temp.getClass().getDeclaredField("currentDate");
        field.setAccessible(true);
        field.set(temp,"20190929");
        final String results=temp.getCurrentDate();
        assertEquals("20190929",results);
        field.set(temp,"20190404");
        final String newresult=temp.getCurrentDate();
        assertEquals("20190404",newresult);
    }
    @Test
    public void changeCheckedDate(){
        final Month temp=new Month();
        CalendarDay day=CalendarDay.from(2019,9,29);
        final String results=temp.changeCheckedDate(day);
        assertEquals("20191029",results);
        day=CalendarDay.from(2019,4,04);
        final String newresults=temp.changeCheckedDate(day);
        assertEquals("20190504",newresults);
        day=CalendarDay.from(2019,11,10);
        final String myresults=temp.changeCheckedDate(day);
        assertEquals("20191210",myresults);

    }
    @Test
    public void getMonthName(){
        final Month temp=new Month();
        final String results=temp.getMonthName(9);
        assertEquals("September",results);
        final String results1=temp.getMonthName(1);
        assertEquals("January",results1);
        final String results2=temp.getMonthName(2);
        assertEquals("February",results2);
        final String results3=temp.getMonthName(3);
        assertEquals("March",results3);
        final String results4=temp.getMonthName(4);
        assertEquals("April",results4);
        final String results5=temp.getMonthName(5);
        assertEquals("May",results5);
        final String results6=temp.getMonthName(6);
        assertEquals("June",results6);
        final String results7=temp.getMonthName(7);
        assertEquals("July",results7);
        final String results8=temp.getMonthName(8);
        assertEquals("August",results8);
        final String results10=temp.getMonthName(10);
        assertEquals("October",results10);
        final String results11=temp.getMonthName(11);
        assertEquals("November",results11);
        final String results12=temp.getMonthName(12);
        assertEquals("December",results12);
    }
    @Test
    public void getMonthIndex() throws NoSuchFieldException, IllegalAccessException{
        final Month temp=new Month();
         final Field field=temp.getClass().getDeclaredField("checkedDate");
        field.setAccessible(true);
        field.set(temp,"20190929");
        final int results=temp.getMonthIndex();
        assertEquals(9,results);
    }

}
