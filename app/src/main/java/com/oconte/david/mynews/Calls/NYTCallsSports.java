package com.oconte.david.mynews.Calls;

import android.support.annotation.Nullable;

import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.NYTFactory;
import com.oconte.david.mynews.NYTService;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYTCallsSports {

    /**
     * It's the Call to API New York Time for see the Sports categories.
     */

    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable Result results);
        void onFailure();
    }

    // Public methode to start fetching
    public static void getSports(NYTService nytService,NYTCallsTopStories.Callbacks callbacks, String section) {

        // weak reference to callback (avoid memory leaks)
        final WeakReference<NYTCallsTopStories.Callbacks> callbacksWeakReference = new WeakReference<NYTCallsTopStories.Callbacks>(callbacks);

        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Result> response = nytService.getSports(section).execute();
                    if (response.isSuccessful()) {
                        // Call the proper callback used in controller mainfragment
                        if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                    } else {
                        // Call the proper callback used in controller mainfragment
                        if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
                    }
                } catch (Exception e) {
                    // Call the proper callback used in controller mainfragment
                    if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
                }

            }
        });

    }
}
