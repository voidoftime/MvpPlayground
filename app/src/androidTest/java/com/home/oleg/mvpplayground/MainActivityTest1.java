package com.home.oleg.mvpplayground;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest1() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.leftList), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.leftList), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.leftList), isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.rightList), isDisplayed()));
        recyclerView4.perform(actionOnItemAtPosition(2, click()));

    }

}
