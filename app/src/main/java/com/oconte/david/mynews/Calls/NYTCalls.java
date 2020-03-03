package com.oconte.david.mynews.Calls;

import android.support.annotation.Nullable;

import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.NYTService;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYTCalls {

    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable Result results);
        void onFailure();
    }

    // Public methode to start fetching
    public static void getTopStories(Callbacks callbacks, String section) {

        // weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // Get Retrofit instance and the related endpoints
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        // The call on NYT API
        Call<Result> call = nytService.getFollowing(section);

        // Start the Call
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                // Call the proper callback used in controller mainfragment
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                // Call the proper callback used in controller mainfragment
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
