package com.oconte.david.mynews;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
        this.context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void checkMenuToolBar_onClickItem_Noti() {

        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());

        onView(withText("Notifications")).perform(click());

        onView(withId(R.id.notification_query_term)).check(matches(isDisplayed()));
    }

    @Test
    public void checkMenuToolBar_onClickItem_Help() {

        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());

        onView(withText("Help")).perform(click());
    }

    @Test
    public void checkMenuToolBar_onClickItem_About() {

        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());

        onView(withText("About")).perform(click());
    }

    @Test
    public void checkMenuToolBar_onClickItem_Search() {

        onView(ViewMatchers.withId(R.id.toolbar)).perform(click());

        onView(withId(R.id.menu_main_activity_search)).perform(click());
    }

}
