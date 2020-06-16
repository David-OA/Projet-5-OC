package com.oconte.david.mynews;

import com.oconte.david.mynews.Calls.NYTCallsMostPopular;
import com.oconte.david.mynews.Calls.NYTCallsSearch;
import com.oconte.david.mynews.Calls.NYTCallsSports;
import com.oconte.david.mynews.Calls.NYTCallsTopStories;
import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.Utils.ConfigureDate;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyNewUnitTest  {

    @Test
    public void testCallsForSearch() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);

        Call<SearchResult> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.success(null));

        NYTCallsSearch.Callbacks callbacks = Mockito.mock(NYTCallsSearch.Callbacks.class);

        when(service.getSearchSection("01/02/2020", "16/05/2020", "sports", "kobe", 10)).thenReturn(call);

        NYTCallsSearch.getSearchSection(service,callbacks, "01/02/2020", "16/05/2020", "sports", "kobe", 10);

        verify(callbacks).onResponse(any());

    }

    @Test
    public void testCallsTopStories() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);

        Call<Result> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.success(null));

        NYTCallsTopStories.Callbacks callbacks = Mockito.mock(NYTCallsTopStories.Callbacks.class);

        when(service.getTopStories( "home")).thenReturn(call);

        NYTCallsTopStories.getTopStories(service,callbacks, "home");

        verify(callbacks).onResponse(any());

    }

    /*@Test
    public void testCallsSports() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);

        Call<Result> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.success(null));

        NYTCallsSports.Callbacks callbacks = Mockito.mock(NYTCallsSports.Callbacks.class);

        when(service.getSports( "sports")).thenReturn(call);

        NYTCallsSports.getSports(service,callbacks, "sports");

        verify(callbacks).onResponse(any());

    }*/

    @Test
    public void testCallsTMostPopular() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);

        Call<Result> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.success(null));

        NYTCallsMostPopular.Callbacks callbacks = Mockito.mock(NYTCallsMostPopular.Callbacks.class);

        when(service.getMostPopular( "home")).thenReturn(call);

        NYTCallsMostPopular.getMostPopular(service,callbacks, "home");

        verify(callbacks).onResponse(any());

    }

    /*@Test
    public void testFailureCalls() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);

        Call<SearchResult> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.error(400, ResponseBody.create(MediaType.get("application/json"), "()")));

        NYTCallsSearch.Callbacks callbacks = Mockito.mock(NYTCallsSearch.Callbacks.class);

        when(service.getSearchSection("01/02/2020", "16/05/2020", "sports", "kobe", 10)).thenReturn(call);

        NYTCallsSearch.getSearchSection(service,callbacks, "01/02/2020", "16/05/2020", "sports", "kobe", 10);

        verify(callbacks).onFailure();

    }*/

}