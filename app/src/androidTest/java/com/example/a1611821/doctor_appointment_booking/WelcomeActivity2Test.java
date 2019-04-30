package com.example.a1611821.doctor_appointment_booking;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class WelcomeActivity2Test {

    @Rule
    public ActivityTestRule<WelcomeActivity2> mActivityTestRule= new ActivityTestRule<>(WelcomeActivity2.class);
    private WelcomeActivity2 mActivity=null;

    //Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(LoginActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorLogIn= getInstrumentation().addMonitor(WelcomeActivity2.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void RadioGroupTest(){
        assertNotNull(mActivity.findViewById(R.id.sex));
        assertNotNull(mActivity.findViewById(R.id.male));
        assertNotNull(mActivity.findViewById(R.id.female));
        assertNotNull(mActivity.findViewById(R.id.other));
    }

    @Test
    public void EditViewsTest(){
        assertNotNull(mActivity.findViewById(R.id.fullnames));
        assertNotNull(mActivity.findViewById(R.id.Surname));
        assertNotNull(mActivity.findViewById(R.id.email));
        assertNotNull(mActivity.findViewById(R.id.mobile));
        assertNotNull(mActivity.findViewById(R.id.identity_no));
        assertNotNull(mActivity.findViewById(R.id.password));
        assertNotNull(mActivity.findViewById(R.id.confirmation));

    }

    /*@Test
    public void EditTextHintsTest(){
        onView(withId(R.id.fullnames)).check(matches(withHint("Firstname)));
        onView(withId(R.id.Surname)).check(matches(withHint("Surname")));
        onView(withId(R.id.email)).check(matches(withHint("Email")));
        onView(withId(R.id.mobile)).check(matches(withHint("Mobile")));
        onView(withId(R.id.identity_no)).check(matches(withHint("Identity Number")));
        onView(withId(R.id.password)).check(matches(withHint("Password")));
        onView(withId(R.id.confirmation)).check(matches(withHint("Confirm Password")));

    }*/

    @Test
    public void radioTestFeMaleChecked(){
        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));
        onView(withId(R.id.male)).check(matches(not(isChecked())));
        onView(withId(R.id.other)).check(matches(not(isChecked())));
        //pressBack();
    }



    // @Test
    public void radioTestMaleChecked(){
        onView(withId(R.id.male)).perform(click());
        // onView(withId(R.id.other)).check(matches(isChecked()));
        onView(withId(R.id.female)).check(matches(not(isChecked())));
        onView(withId(R.id.other)).check(matches(not(isChecked())));
        onView(withId(R.id.male)).check(matches(withText("Male"))).check(matches(isChecked()));
    }


    // @Test
    public void radioTestOtherChecked(){
        onView(withId(R.id.other)).perform(click());
        // onView(withId(R.id.other)).check(matches(isChecked()));
        onView(withId(R.id.female)).check(matches(not(isChecked())));
        onView(withId(R.id.male)).check(matches(not(isChecked())));
        onView(withId(R.id.other)).check(matches(withText("Other"))).check(matches(isChecked()));
        //pressBack();
    }

    @Test
    public void TextViewTest(){
        assertNotNull(mActivity.findViewById(R.id.createAccount));
       // onView(withId(R.id.createAccount)).perform(scrollTo()).check(matches(isCompletelyDisplayed()));
        //onView(withId(R.id.createAccount)).perform(scrollTo(),click());


    }



    @Test
    public void ExistingAccountTest(){
        //onView(withId(R.id.fullnames)).perform(typeText("Julia"));
        onView(withId(R.id.fullnames)).perform(typeText("Julia"), closeSoftKeyboard());
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Surname)).perform(typeText("Matthews"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmail.com"), closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.identity_no)).perform(typeText("9005125698085"), closeSoftKeyboard());


        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(), typeText("0862198754"),closeSoftKeyboard());


        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"));
        //close keyboard
        Espresso.closeSoftKeyboard();

        //onView(withId(R.id.createAccount)).perform(click());
        onView(withId(R.id.createAccount)).perform(scrollTo(), click());

        WelcomeActivity2 activity = mActivityTestRule.getActivity();
        //change String
        onView(withText("Account already exist")).inRoot(withDecorView(CoreMatchers.not(CoreMatchers.is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));


    }


    //@Test
    public void CreateAccountTest(){

        Random rand = new Random();

        int contactInt = rand.nextInt(900000000) + 100000000;
        String contactInFo = Integer.toString(contactInt);
        int lastSevenDigits = rand.nextInt(9000000) + 1000000;
        String lastSevenDigitsString = Integer.toString(lastSevenDigits);

        int year = rand.nextInt(90) + 10;

        String yearBorn=Integer.toString(year);



        int targetLengthPassword = rand.nextInt(9) + 9;

        //generate the strings

        int leftLimit = 97; // letter 'a'

        int rightLimit = 122; // letter 'z'

        int targetStringLength = rand.nextInt(2)+4;


        StringBuilder buffer = new StringBuilder(targetStringLength);
        StringBuilder surname = new StringBuilder(targetStringLength);//StringBuilder surname = new StringBuilder(targetStringLength);
        StringBuilder password = new StringBuilder(5);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (rand.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (rand.nextFloat() * (rightLimit - leftLimit + 1));
            surname.append((char) randomLimitedInt);
        }

        for (int i = 0; i < targetLengthPassword; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (rand.nextFloat() * (rightLimit - leftLimit + 1));
            password.append((char) randomLimitedInt);
        }

        String firstName = buffer.toString();

        String Surname=surname.toString();

        String PasswordGen=password.toString();




        onView(withId(R.id.fullnames)).perform(typeText(firstName));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Surname)).perform(typeText(Surname));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText(firstName+Surname+"@gmail.com"),closeSoftKeyboard());
        //close keyboard

        //generate the first six numbers
        onView(withId(R.id.identity_no)).perform(scrollTo(),typeText(yearBorn+"0420"+lastSevenDigitsString),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        //onView(withId(R.id.female)).check(matches(isChecked()));
        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0"+contactInFo),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText(PasswordGen),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText(PasswordGen),closeSoftKeyboard());
        //close keyboard

        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        WelcomeActivity2 activity = mActivityTestRule.getActivity();




        Activity LoginActivity =getInstrumentation().waitForMonitorWithTimeout(monitorLogIn,5000);
        assertNotNull(LoginActivity);

        onView(withText("You have been registered")).inRoot(withDecorView(CoreMatchers.not(CoreMatchers.is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));

        LoginActivity.finish();

    }

    @Test
    public void SexNotSelected(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Surname)).perform(typeText("Matthews"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmail.com"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.identity_no)).perform(typeText("9005125698085"),closeSoftKeyboard());
        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0862198754"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());

        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        WelcomeActivity2 activity = mActivityTestRule.getActivity();
        onView(withText("Please select gender")).inRoot(withDecorView(CoreMatchers.not(CoreMatchers.is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));


    }


    @Test
    public void checkMatchingPassword(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Surname)).perform(typeText("Matthews"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmail.com"));
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.identity_no)).perform(typeText("9005125698085"));


        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0862198754"),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman13141"),closeSoftKeyboard());
        //close keyboard

        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.confirmation)).check(matches(hasErrorText("Passwords do not match")));

    }

    @Test
    public void checkEmptyFields(){
        //onView(withId(R.id.identity_no)).perform(typeText(""));
        //close keyboard
        //Espresso.closeSoftKeyboard();

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.password)).perform(scrollTo(),typeText(""),closeSoftKeyboard());
        //close keyboard

        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.fullnames)).check(matches(hasErrorText("This field cannot be empty")));
        onView(withId(R.id.Surname)).check(matches(hasErrorText("This field cannot be empty")));
        onView(withId(R.id.mobile)).check(matches(hasErrorText("This field cannot be empty")));
        onView(withId(R.id.email)).check(matches(hasErrorText("This field cannot be empty")));
        onView(withId(R.id.identity_no)).check(matches(hasErrorText("This field cannot be empty")));
        onView(withId(R.id.password)).check(matches(hasErrorText("This field cannot be empty")));


    }


    @Test
    public void check_ValidName_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Ju"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.Surname)).perform(typeText("Matthews"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmailcom"),closeSoftKeyboard());

        onView(withId(R.id.identity_no)).perform(typeText("9002055598042"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0728456948"),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
         onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.fullnames)).check(matches(hasErrorText("Enter a valid first name")));
    }

    @Test
    public void check_ValidSurName_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.Surname)).perform(typeText("Ma"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmailcom"),closeSoftKeyboard());
        //close keyboard
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.identity_no)).perform(typeText("9002055598042"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0728456948"),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.Surname)).check(matches(hasErrorText("Enter a valid surname")));

    }

    @Test
    public void check_ValidEmail_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.Surname)).perform(typeText("Matthews"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.email)).perform(typeText("JuliaMatthewsgmailcom"),closeSoftKeyboard());

        onView(withId(R.id.identity_no)).perform(typeText("9002055598042"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0728456948"),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.email)).check(matches(hasErrorText("Enter a valid email")));

    }


    @Test
    public void check_ValidIdentity_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.Surname)).perform(typeText("Matthews"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmailcom"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.identity_no)).perform(typeText("9002055598sss"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0728456948"),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.identity_no)).check(matches(hasErrorText("Enter a valid identity number")));

    }

    @Test
    public void check_ValidMobile_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.Surname)).perform(typeText("Matthews"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmailcom"),closeSoftKeyboard());

        onView(withId(R.id.identity_no)).perform(typeText("9002055598042"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("072845694d8"),closeSoftKeyboard());

        onView(withId(R.id.password)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("@Batman131410"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.createAccount)).perform(scrollTo(),click());

        onView(withId(R.id.mobile)).check(matches(hasErrorText("Enter a valid phone number")));

    }


   @Test
    public void check_passwordMinChar_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"),closeSoftKeyboard());
        onView(withId(R.id.Surname)).perform(typeText("Matthews"),closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmail.com"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.identity_no)).perform(typeText("0902035598082"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0862198754"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(scrollTo(),typeText("1234"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("1234"),closeSoftKeyboard());
        //close keyboard
        onView(withId(R.id.createAccount)).perform(scrollTo(),click());
        onView(withId(R.id.password)).check(matches(hasErrorText("Password must at least be 6 characters")));

    }

    @Test
    public void check_ageRestriction_pass(){
        onView(withId(R.id.fullnames)).perform(typeText("Julia"),closeSoftKeyboard());
        onView(withId(R.id.Surname)).perform(typeText("Matthews"),closeSoftKeyboard());

        onView(withId(R.id.email)).perform(typeText("JuliaMatthews@gmail.com"),closeSoftKeyboard());

        onView(withId(R.id.identity_no)).perform(typeText("0302035598082"),closeSoftKeyboard());

        onView(withId(R.id.female)).perform(scrollTo(),click());
        onView(withId(R.id.female)).check(matches(isChecked()));

        onView(withId(R.id.mobile)).perform(scrollTo(),typeText("0862198754"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(scrollTo(),typeText("1234545ww"),closeSoftKeyboard());
        onView(withId(R.id.confirmation)).perform(scrollTo(),typeText("1234545ww"),closeSoftKeyboard());

        onView(withId(R.id.createAccount)).perform(scrollTo(),click());
        WelcomeActivity2 activity = mActivityTestRule.getActivity();
        onView(withText("You must be 18 years or older to register")).inRoot(withDecorView(CoreMatchers.not(CoreMatchers.is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
    

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}
