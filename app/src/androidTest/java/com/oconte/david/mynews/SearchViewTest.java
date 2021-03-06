package com.oconte.david.mynews;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.oconte.david.mynews.di.Injection;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SearchViewTest {

    /**
     * such a {@link ActivityTestRule} can be used eg. for Intent.putExtra(),
     * alike one would pass command-line arguments to regular run configurations.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class, false, false);

    // Method allowing to outsource the creation of the mockwebserver object by passing the correct http code (200, 400 or other) and the correct json response.
    private MockWebServer setupServer(int code, String response) {

        MockWebServer server = new MockWebServer();
        server.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                return new MockResponse().setResponseCode(code).setBody(response);
            }

        });
        return server;

    }

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(Injection.resource);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(Injection.resource);
    }

    @Test
    public void testCallsSearch() throws IOException, InterruptedException {

        MockWebServer server = setupServer(HttpURLConnection.HTTP_OK, AssetReader.getAsset(InstrumentationRegistry.getInstrumentation().getContext(), "search_response.json"));

        // Start the server.
        server.start(9903);

        //Start the MainActivity
        mActivityRule.launchActivity(null);

        //Test recyclerview is good.
        onView(withId(R.id.fragment_main_recycler_view)).check(matches(isDisplayed()));

        // Check the click on a item of the recyclerview
        onView(withId(R.id.fragment_main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        // Check the webView is good.
        onView(withId(R.id.web_view_all_new)).check(matches(isDisplayed()));

    }

}
