package com.oconte.david.mynews;

import android.app.ActionBar;
import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    private Context context ;

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getTargetContext();
    }


    @Test
    public void checkMenuToolBar_onClickItem_Noti() {

        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText("Notifications")).perform(click());

    }

    @Test
    public void checkMenuToolBar_onClickItem_Help() {

        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText("Help")).perform(click());

    }

    @Test
    public void checkMenuToolBar_onClickItem_About() {

        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText("About")).perform(click());

    }

    @Test
    public void checkMenuToolBar_onClickItem_Search() {

        onView(ViewMatchers.withId(R.id.toolbar)).perform(click());

        onView(withId(R.id.menu_main_activity_search)).perform(click());

    }


}
