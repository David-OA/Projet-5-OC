package com.oconte.david.mynews.calls;

import androidx.annotation.Nullable;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.oconte.david.mynews.models.Result;
import com.oconte.david.mynews.NYTService;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYTCallsMostPopular {

    private final NYTService service;
    private final CountingIdlingResource resource;

    /**
     * It's the Call to API New York Time for see the Most popular categories.
     */

    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable Result results);
        void onFailure();
    }

    public NYTCallsMostPopular(NYTService service, CountingIdlingResource resource) {
        this.service = service;
        this.resource = resource;
    }

    // Public methode to start fetching
    public void getMostPopular(NYTCallsMostPopular.Callbacks callbacks, String section) {

        resource.increment();

        // weak reference to callback (avoid memory leaks)
        final WeakReference<NYTCallsMostPopular.Callbacks> callbacksWeakReference = new WeakReference<NYTCallsMostPopular.Callbacks>(callbacks);

        // The call on NYT API
        Call<Result> call = service.getMostPopular(section);

        // Start the Call
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                // Call the proper callback used in controller mainfragment
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                resource.decrement();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                // Call the proper callback used in controller mainfragment
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
                resource.decrement();
            }
        });

    }
}
