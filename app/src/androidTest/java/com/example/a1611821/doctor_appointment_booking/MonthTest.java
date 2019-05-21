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
    }
    @Test
    public void changeCheckedDate(){
        final Month temp=new Month();
        CalendarDay day=CalendarDay.from(2019,9,29);
        final String results=temp.changeCheckedDate(day);
        assertEquals("20191029",results);

    }
    @Test
    public void getMonthName(){
        final Month temp=new Month();
        final String results=temp.getMonthName(9);
        assertEquals("September",results);
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
