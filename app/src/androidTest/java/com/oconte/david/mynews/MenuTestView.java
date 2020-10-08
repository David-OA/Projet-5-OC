package com.oconte.david.mynews;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MenuTestView {

    /**
     * such a {@link ActivityTestRule} can be used eg. for Intent.putExtra(),
     * alike one would pass command-line arguments to regular run configurations.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class, false, false);

    @Test
    public void testClickActionBarItemSearch() throws IOException, InterruptedException {

        //Start the MainActivity
        mActivityRule.launchActivity(null);

        //Select the toolbar
        onView(withId(R.id.toolbar)).perform(click());

        //Choice the search menu
        onView(withId(R.id.menu_main_activity_search)).perform(click());

        //Check the EditText
        onView(withId(R.id.query_term)).check(matches(isDisplayed()));

        //Check if the sports checkbox is check
        onView(withId(R.id.search_item_sport)).check(matches(isNotChecked()));

        //Check the search button.
        onView(withId(R.id.search_button)).check(matches(withText("SEARCH")));
    }

    @Test
    public  void testClickActionBarMenuNotification() throws IOException, InterruptedException {

        //Start the MainActivity
        mActivityRule.launchActivity(null);

        //Select the toolbar
        onView(withId(R.id.toolbar)).perform(click());

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());

        //Choice the Notification menu
        onView(withText("Notifications")).perform(click());

        //Check the Travel filtered.
        onView(withId(R.id.notification_item_travel)).check(matches(withText("Travel")));

        //Check the switch button is here.
        onView(withId(R.id.notification_switch)).check(matches(isDisplayed()));
    }

    @Test
    public  void testClickActionBarMenuHelp() throws IOException, InterruptedException {

        //Start the MainActivity
        mActivityRule.launchActivity(null);

        //Select the toolbar
        onView(withId(R.id.toolbar)).perform(click());

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());

        //Choice the help menu
        onView(withText("Help")).perform(click());

        //Check the TextView is here.
        onView(withId(R.id.text_help)).check(matches(isDisplayed()));
    }

    @Test
    public  void testClickActionBarMenuAbout() throws IOException, InterruptedException {

        //Start the MainActivity
        mActivityRule.launchActivity(null);

        //Select the toolbar
        onView(withId(R.id.toolbar)).perform(click());

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());

        //Choice the About menu
        onView(withText("About")).perform(click());

        //Check the Markdown is here.
        onView(withId(R.id.markdown_view_view)).check(matches(isDisplayed()));
    }

}
