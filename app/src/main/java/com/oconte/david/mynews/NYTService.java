package com.oconte.david.mynews;

import com.oconte.david.mynews.models.Result;
import com.oconte.david.mynews.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTService {
    /**
     * It's all calls to NYT API for section I need.
     */

    @GET("/svc/topstories/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getTopStories(@Path("section") String section);

    @GET("/svc/mostpopular/v2/{section}/1.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getMostPopular(@Path("section") String section);

    @GET("/svc/topstories/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getSports(@Path("section") String section);

    @GET("/svc/search/v2/articlesearch.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<SearchResult> getSearchSection(
            @Query("Begin Date") String beginDate,
            @Query("End Date") String endDate,
            @Query("fq") String querySection,
            @Query("q") String queryTerm,
            @Query("page") int pageNumber
    );
}
