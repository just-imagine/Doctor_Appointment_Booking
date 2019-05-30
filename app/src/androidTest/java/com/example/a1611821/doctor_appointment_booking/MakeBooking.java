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
import static android.support.test.espresso.action.ViewActions.longClick;
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
public class MakeBooking {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void makeBooking() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("t"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.username), withText("t"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.username), withText("t"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("tmavhona@gmail.com"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.username), withText("tmavhona@gmail.com"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.username), withText("tmavhona@gmail.com"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("afterlife"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.password), withText("afterlife"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login), withText("Login"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

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

        ViewInteraction dayView = onView(
                allOf(withText("9"), withContentDescription("9"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                16),
                        isDisplayed()));
        dayView.perform(longClick());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Free"),
                        childAtPosition(
                                allOf(withId(R.id.d),
                                        childAtPosition(
                                                withId(R.id.sample),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.bookslot), withText("Book Slot"),
                        childAtPosition(
                                allOf(withId(R.id.pop),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());
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
