package com.oconte.david.mynews.di;

import androidx.test.espresso.idling.CountingIdlingResource;

import com.oconte.david.mynews.Calls.NYTCallsMostPopular;
import com.oconte.david.mynews.Calls.NYTCallsSports;
import com.oconte.david.mynews.Calls.NYTCallsTopStories;
import com.oconte.david.mynews.NYTFactory;
import com.oconte.david.mynews.NYTService;

public class Injection {

    public static NYTCallsTopStories getTopStories(NYTService service, CountingIdlingResource resource) {
        return new NYTCallsTopStories(service, resource);
    }

    public static  NYTService getService(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCounting() {
        return new CountingIdlingResource("TopStoriesIdling");
    }

    // Pour le Calls Sports
    public static NYTCallsSports getSports(NYTService service, CountingIdlingResource resource) {
        return new NYTCallsSports(service, resource);
    }

    public static  NYTService getServiceSports(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCountingSports() {
        return new CountingIdlingResource("SportsIdling");
    }

    // Pour le Calls MostPopular
    public static NYTCallsMostPopular getMostPopular(NYTService service, CountingIdlingResource resource) {
        return new NYTCallsMostPopular(service, resource);
    }

    public static  NYTService getServiceMostPopular(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCountingMostPopular() {
        return new CountingIdlingResource("MostPopularIdling");
    }
}
