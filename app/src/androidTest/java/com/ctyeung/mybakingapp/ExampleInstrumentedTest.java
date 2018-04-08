package com.ctyeung.mybakingapp;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.DataInteraction;
import android.support.v7.widget.RecyclerView;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ctyeung.mybakingapp", appContext.getPackageName());
    }

    @Test
    public void itemNutellaIsDisplayed() {
        onView(withText("Nutella Pie")).check(matches(isDisplayed()));
    }

    @Test
    public void AllItemsDisplayed() {
        onView(withId (R.id.recipe_list)).check(matches(withListSize(4)));
    }

    private static Matcher<View> withListSize(final int count) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view)
            {
                RecyclerView recyclerView = view.findViewById(R.id.recipe_list);
                int numChildren = recyclerView.getChildCount();
                return (count==numChildren)?true:false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("");
            }
        };
    }
}
