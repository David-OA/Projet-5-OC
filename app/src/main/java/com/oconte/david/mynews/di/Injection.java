package com.oconte.david.mynews.di;

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
        return new NYTCallsTopStories(service, resource);
    }

    public static  NYTService getService(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCounting() {
        return new CountingIdlingResource("TopStoriesIdling");
    }

    // For Calls Sports
    public static NYTCallsSports getSports(NYTService service, CountingIdlingResource resource) {
        return new NYTCallsSports(service, resource);
    }

    public static  NYTService getServiceSports(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCountingSports() {
        return new CountingIdlingResource("SportsIdling");
    }

    // For Calls MostPopular
    public static NYTCallsMostPopular getMostPopular(NYTService service, CountingIdlingResource resource) {
        return new NYTCallsMostPopular(service, resource);
    }

    public static  NYTService getServiceMostPopular(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCountingMostPopular() {
        return new CountingIdlingResource("MostPopularIdling");
    }

    // For Calls Search
    public static NYTCallsSearch getSearch(NYTService service, CountingIdlingResource resource) {
        return new NYTCallsSearch(service, resource);
    }

    public static  NYTService getServiceSearch(){
        return NYTFactory.getRetrofit().create(NYTService.class);
    }

    public  static  CountingIdlingResource getCountingSearch() {
        return new CountingIdlingResource("MostPopularIdling");
    }
}
