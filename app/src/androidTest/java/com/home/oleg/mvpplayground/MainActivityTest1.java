package com.home.oleg.mvpplayground;


import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.test.di.TestItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.items.match.model.WordPair;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest1 {

    ItemsMatchWordsProvider itemsMatchWordsProvider;

    @Inject
    public void setItemsMatchWordsProvider(ItemsMatchWordsProvider itemsMatchWordsProvider) {
        this.itemsMatchWordsProvider = itemsMatchWordsProvider;
    }

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False so we can customize the intent per test method

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MainApp app= (MainApp) instrumentation.getTargetContext().getApplicationContext();
//        component.inject(this);

    }
@Inject
    public MainActivityTest1() {
            System.out.println("CONSTR ");
    TestItemsMatchWordsProvider.words = Arrays.asList(new WordPair("a", "1"));

    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest1() {
        System.out.println("AS ASD" +this.itemsMatchWordsProvider);
//        System.out.println("itemsMatchWordsProvider " + itemsMatchWordsProvider);
//        TestItemsMatchWordsProvider.words.onNext(Arrays.asList(new WordPair("a", "1")));

        ViewInteraction foreignWordsListView = onView(allOf(withId(R.id.leftList), isDisplayed()));
        foreignWordsListView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction nativeWordsListView = onView(allOf(withId(R.id.rightList), isDisplayed()));
        nativeWordsListView.perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.leftList)).check(new RecyclerViewItemsCountAssertion(0));
        onView(withId(R.id.rightList)).check(new RecyclerViewItemsCountAssertion(0));
    }

}
