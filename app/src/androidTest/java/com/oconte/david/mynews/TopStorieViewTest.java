package com.oconte.david.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.oconte.david.mynews.Models.Result;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TopStorieViewTest {

    private Result results;
    private String baseUrl = "http://127.0.0.1:9900";

    // une methode permettant dexternaliser la creation de lobjet mockwebserver en lui passant le bon code http(200, 400 ou autre) et la bonne reponse json
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

    @Test
    public void testCallsTopStorie() throws IOException, InterruptedException {

        MockWebServer server = setupServer(HttpURLConnection.HTTP_OK, AssetReader.getAsset(InstrumentationRegistry.getInstrumentation().getContext(), "topstories_response.json"));

        final CountDownLatch latch = new CountDownLatch(1);

        // Start the server.
        server.start(9900);


        mActivityRule.getActivity().getFragmentManager().beginTransaction();
        FragmentTopStories fragmentTopStories = new FragmentTopStories();
        // lancer activity sur le bon fragment
        /*mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity activity = (MainActivity) mActivityRule.getActivity();
                FragmentTopStories fragmentTopStories = activity.getSupportFragmentManager().getFragments().add(0,FragmentTopStories);
                TopStoriesFragments topStoriesFragment = new FragmentTopStories();
                return topStoriesFragment;
            }
        });mActivityruleLunch

        onView(withId(R.id.fragment_main_date)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_main_image)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_main_section)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_main_title)).check(matches(isDisplayed()));*/

    }

    @Before
    public void setUpFragment(){

    }


    /* the Activity of the Target application*/
    private MainActivity mActivity;

    /** the {@link RecyclerView}'s resource id*/
    private int resId = R.id.fragment_main_recycler_view;

    /** the {@link RecyclerView}*/
    private RecyclerView mRecyclerView;

    /** and it's item count*/
    private int itemCount = 0;

    /**
     * such a {@link ActivityTestRule} can be used eg. for Intent.putExtra(),
     * alike one would pass command-line arguments to regular run configurations.
     *
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class,false, false) {

        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            intent.putExtras(extras);
            return intent;
        }
    };

    @Before
    public void setUpTest() {

        /*obtaining the Activity from the ActivityTestRule*/
        this.mActivity = this.mActivityRule.getActivity();

        /* obtaining handles to the Ui of the Activity*/
        this.mRecyclerView = this.mActivity.findViewById(this.resId);
        this.itemCount = this.mRecyclerView.getAdapter().getItemCount();
    }

    @Test
    public void TopStorieViewTest() {
        if(this.itemCount > 0) {
            for(int i=0; i < this.itemCount; i++) {

                /* clicking the item*/
                //onView(withId(this.resId))
                        //.perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

                /* check if the ViewHolder is being displayed*/
                onView(new RecyclerViewMatcher(this.resId)
                        .atPositionOnView(i, R.id.fragment_main_recycler_view))
                        .check(matches(isDisplayed()));

                /* checking for the text of the first one item*/
                if(i == 0) {
                    onView(new RecyclerViewMatcher(this.resId)
                            .atPositionOnView(i, R.id.fragment_main_section))
                            .check(matches(withText("Farbstoffe")));
                }

                if(i == 0) {
                    onView(new RecyclerViewMatcher(this.resId)
                            .atPositionOnView(i, R.id.fragment_main_title))
                            .check(matches(withText("Farbstoffe")));
                }

            }
        }
    }
}
