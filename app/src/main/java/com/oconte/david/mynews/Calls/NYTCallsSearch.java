package com.oconte.david.mynews.Calls;

import android.support.annotation.Nullable;

import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.NYTFactory;
import com.oconte.david.mynews.NYTService;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYTCallsSearch {

    /**
     * It's the Call to API New York Time for see the Search categories.
     */

    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable SearchResult response);
        void onFailure();
    }

    // Public methode to start fetching
    public static void getSearchSection(NYTService service, NYTCallsSearch.Callbacks callbacks, String beginDate, String endDate, String querySection, String queryTerm, int pageNumber) {

        // weak reference to callback (avoid memory leaks)
        //final WeakReference<NYTCallsSearch.Callbacks> callbacksWeakReference = new WeakReference<NYTCallsSearch.Callbacks>(callbacks);

        // Get Retrofit instance and the related endpoints
        //NYTService nytService = NYTFactory.getRetrofit().create(NYTService.class);

        // The call on NYT API
        Call<SearchResult> call = service.getSearchSection(beginDate, endDate, querySection, queryTerm, pageNumber);

        // Start the Call
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                // Call the proper callback used in controller mainfragment
                //if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                callbacks.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

                // Call the proper callback used in controller mainfragment
                //if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
                callbacks.onFailure();
            }
        });

    }
}
