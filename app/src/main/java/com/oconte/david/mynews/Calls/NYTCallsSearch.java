package com.oconte.david.mynews.Calls;

import androidx.annotation.Nullable;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.NYTService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYTCallsSearch {

    private final NYTService service;
    private final CountingIdlingResource resource;

    /**
     * It's the Call to API New York Time for see the Search categories.
     */

    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable SearchResult response);
        void onFailure();
    }

    public NYTCallsSearch(NYTService service, CountingIdlingResource resource) {
        this.service = service;
        this.resource = resource;
    }

    // Public method to start fetching
    public void getSearchSection(NYTService service, NYTCallsSearch.Callbacks callbacks, String beginDate, String endDate, String querySection, String queryTerm, int pageNumber) {

        resource.increment();

        // The call on NYT API
        Call<SearchResult> call = service.getSearchSection(beginDate, endDate, querySection, queryTerm, pageNumber);

        // Start the Call
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                // Call the proper callback used in controller mainfragment
                callbacks.onResponse(response.body());
                resource.decrement();

            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

                // Call the proper callback used in controller mainfragment
                callbacks.onFailure();
                resource.decrement();
            }
        });

    }
}
