package com.example.a1611821.doctor_appointment_booking;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MovingTest {

    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule = new ActivityTestRule<>(HomeScreen.class);

   // @Test
    public void movingTest() {


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction directionButton = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton.perform(click());

        ViewInteraction directionButton2 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton2.perform(click());

        ViewInteraction directionButton3 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton3.perform(click());

        ViewInteraction directionButton4 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton4.perform(click());

        ViewInteraction directionButton5 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton5.perform(click());

        ViewInteraction directionButton6 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton6.perform(click());

        ViewInteraction directionButton7 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton7.perform(click());

        ViewInteraction directionButton8 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton8.perform(click());

        ViewInteraction directionButton9 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton9.perform(click());

        ViewInteraction directionButton10 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton10.perform(click());

        ViewInteraction directionButton11 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton11.perform(click());

        ViewInteraction directionButton12 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton12.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
