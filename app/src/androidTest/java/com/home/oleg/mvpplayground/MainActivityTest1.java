package com.home.oleg.mvpplayground;


import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.testDi.TestItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.items.match.model.WordPair;
import com.home.oleg.mvpplayground.testDi.TestMainComponent;

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
import static com.home.oleg.mvpplayground.testDi.TestApiModule.testItemsMatchWordsProvider;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest1 {

    //    @Inject
    ItemsMatchWordsProvider itemsMatchWordsProvider;

//    @Inject
//    public void setItemsMatchWordsProvider(ItemsMatchWordsProvider itemsMatchWordsProvider) {
//        this.itemsMatchWordsProvider = itemsMatchWordsProvider;
//    }

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class,
            true,     // initialTouchMode
            false){
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            System.out.println("BEFORE");
        }
    };   // launchActivity. False so we can customize the intent per test method

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MainApp app = (MainApp) instrumentation.getTargetContext().getApplicationContext();
//        component.inject(this);
        System.out.println("SET UP");
        app.initComponent();
//        itemsMatchWordsProvider = ((TestMainComponent) app.getMainComponent()).itemsMatchWordsProvider();

    }

    //@Inject
//    public MainActivityTest1() {
//            System.out.println("CONSTR ");
//    TestItemsMatchWordsProvider.words = Arrays.asList(new WordPair("a", "1"));
//
//    }
    static {
//        TestItemsMatchWordsProvider.words = Arrays.asList(new WordPair("a", "1"));
//        testItemsMatchWordsProvider = new TestItemsMatchWordsProvider(Arrays.asList(new WordPair("a", "1")));
    }

//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest1() {
        System.out.println("TEST RUN");
        testItemsMatchWordsProvider = new TestItemsMatchWordsProvider(Arrays.asList(new WordPair("a", "1")));
        activityRule.launchActivity(null);
        System.out.println("AS ASD" + this.itemsMatchWordsProvider);
//        System.out.println("itemsMatchWordsProvider " + itemsMatchWordsProvider);
//        TestItemsMatchWordsProvider.words.onNext(Arrays.asList(new WordPair("a", "1")));

        ViewInteraction foreignWordsListView = onView(allOf(withId(R.id.leftList), isDisplayed()));
        foreignWordsListView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction nativeWordsListView = onView(allOf(withId(R.id.rightList), isDisplayed()));
        nativeWordsListView.perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.leftList)).check(new RecyclerViewItemsCountAssertion(0));
        onView(withId(R.id.rightList)).check(new RecyclerViewItemsCountAssertion(0));
    }
    @Test
    public void mainActivityTest2() {
        System.out.println("TEST RUN 2");
        testItemsMatchWordsProvider = new TestItemsMatchWordsProvider(Arrays.asList(
                new WordPair("a", "1"),
                new WordPair("b", "2")
                ));
        activityRule.launchActivity(null);
        System.out.println("AS ASD" + this.itemsMatchWordsProvider);
//        System.out.println("itemsMatchWordsProvider " + itemsMatchWordsProvider);
//        TestItemsMatchWordsProvider.words.onNext(Arrays.asList(new WordPair("a", "1")));

        ViewInteraction foreignWordsListView = onView(allOf(withId(R.id.leftList), isDisplayed()));
        foreignWordsListView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction nativeWordsListView = onView(allOf(withId(R.id.rightList), isDisplayed()));
        nativeWordsListView.perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.leftList)).check(new RecyclerViewItemsCountAssertion(1));
        onView(withId(R.id.rightList)).check(new RecyclerViewItemsCountAssertion(1));
    }

}
