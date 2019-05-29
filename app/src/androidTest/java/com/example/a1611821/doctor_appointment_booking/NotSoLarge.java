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
import static android.support.test.espresso.Espresso.pressBack;
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
public class NotSoLarge {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

   // @Test
    public void notSoLarge() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("tmavhona@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("afterlife"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password), withText("afterlife"),
                        childAtPosition(
                                allOf(withId(R.id.card),
                                        childAtPosition(
                                                withClassName(is("android.support.v7.widget.CardView")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

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

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        pressBack();

        ViewInteraction directionButton = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton.perform(click());

        ViewInteraction dayView = onView(
                allOf(withText("5"), withContentDescription("5"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView.perform(longClick());

        pressBack();

        ViewInteraction directionButton2 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton2.perform(click());

        ViewInteraction dayView2 = onView(
                allOf(withText("10"), withContentDescription("10"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView2.perform(click());

        ViewInteraction dayView3 = onView(
                allOf(withText("10"), withContentDescription("10"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView3.perform(longClick());

        pressBack();

        ViewInteraction directionButton3 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton3.perform(click());

        ViewInteraction dayView4 = onView(
                allOf(withText("7"), withContentDescription("7"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView4.perform(longClick());

        pressBack();

        ViewInteraction directionButton4 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton4.perform(click());

        ViewInteraction dayView5 = onView(
                allOf(withText("18"), withContentDescription("18"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                24),
                        isDisplayed()));
        dayView5.perform(click());

        ViewInteraction dayView6 = onView(
                allOf(withText("18"), withContentDescription("18"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                24),
                        isDisplayed()));
        dayView6.perform(longClick());

        pressBack();

        ViewInteraction directionButton5 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton5.perform(click());

        ViewInteraction dayView7 = onView(
                allOf(withText("16"), withContentDescription("16"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                24),
                        isDisplayed()));
        dayView7.perform(longClick());

        pressBack();

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
                allOf(withContentDescription("Go to previous"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                0),
                        isDisplayed()));
        directionButton8.perform(click());

        ViewInteraction dayView8 = onView(
                allOf(withText("6"), withContentDescription("6"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                0)),
                                17),
                        isDisplayed()));
        dayView8.perform(click());

        ViewInteraction dayView9 = onView(
                allOf(withText("6"), withContentDescription("6"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                0)),
                                17),
                        isDisplayed()));
        dayView9.perform(longClick());

        pressBack();

        ViewInteraction directionButton9 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton9.perform(click());

        ViewInteraction dayView10 = onView(
                allOf(withText("11"), withContentDescription("11"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView10.perform(longClick());

        pressBack();

        ViewInteraction directionButton10 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton10.perform(click());

        ViewInteraction dayView11 = onView(
                allOf(withText("15"), withContentDescription("15"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                24),
                        isDisplayed()));
        dayView11.perform(longClick());

        pressBack();

        ViewInteraction directionButton11 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton11.perform(click());

        ViewInteraction dayView12 = onView(
                allOf(withText("12"), withContentDescription("12"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                24),
                        isDisplayed()));
        dayView12.perform(longClick());

        pressBack();

        ViewInteraction directionButton12 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton12.perform(click());

        ViewInteraction dayView13 = onView(
                allOf(withText("17"), withContentDescription("17"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                23),
                        isDisplayed()));
        dayView13.perform(longClick());

        pressBack();

        ViewInteraction dayView14 = onView(
                allOf(withText("11"), withContentDescription("11"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView14.perform(click());

        ViewInteraction dayView15 = onView(
                allOf(withText("11"), withContentDescription("11"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView15.perform(longClick());

        pressBack();

        ViewInteraction directionButton13 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton13.perform(click());

        ViewInteraction dayView16 = onView(
                allOf(withText("8"), withContentDescription("8"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                17),
                        isDisplayed()));
        dayView16.perform(longClick());

        pressBack();

        ViewInteraction directionButton14 = onView(
                allOf(withContentDescription("Go to next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.thing),
                                        0),
                                2),
                        isDisplayed()));
        directionButton14.perform(click());

        ViewInteraction dayView17 = onView(
                allOf(withText("14"), withContentDescription("14"),
                        childAtPosition(
                                allOf(withContentDescription("Calendar"),
                                        childAtPosition(
                                                withId(R.id.mcv_pager),
                                                1)),
                                25),
                        isDisplayed()));
        dayView17.perform(longClick());

        pressBack();
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
