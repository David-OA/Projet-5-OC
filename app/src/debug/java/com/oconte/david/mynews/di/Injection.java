package com.oconte.david.mynews.di;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.base.IdlingResourceRegistry_Factory;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.oconte.david.mynews.calls.NYTCallsMostPopular;
import com.oconte.david.mynews.calls.NYTCallsSearch;
import com.oconte.david.mynews.calls.NYTCallsSports;
import com.oconte.david.mynews.calls.NYTCallsTopStories;
import com.oconte.david.mynews.NYTFactory;
import com.oconte.david.mynews.NYTService;

public class Injection {

    // For Calls TopStories
    public static NYTCallsTopStories getTopStories(NYTService service, CountingIdlingResource resource) {
        IdlingRegistry.getInstance().register(resource);
        NYTCallsTopStories topStories = new NYTCallsTopStories(service, resource);
        return topStories;
    }

    public static  NYTService getService(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCounting() {
        return new CountingIdlingResource("NewYorkTimeIdling");
    }

    // For Calls Sports
    public static NYTCallsSports getSports(NYTService service, CountingIdlingResource resource) {
        IdlingRegistry.getInstance().register(resource);
        NYTCallsSports sports = new NYTCallsSports(service, resource);
        return sports;
    }

    // For Calls MostPopular
    public static NYTCallsMostPopular getMostPopular(NYTService service, CountingIdlingResource resource) {
        IdlingRegistry.getInstance().register(resource);
        NYTCallsMostPopular mostPopular = new NYTCallsMostPopular(service, resource);
        return mostPopular;
    }


    // For Calls Search
    public static NYTCallsSearch getSearch(NYTService service, CountingIdlingResource resource) {
        IdlingRegistry.getInstance().register(resource);
        return new NYTCallsSearch(service, resource);
    }

}