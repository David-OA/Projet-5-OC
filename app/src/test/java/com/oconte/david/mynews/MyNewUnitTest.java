package com.oconte.david.mynews;

import com.oconte.david.mynews.Calls.NYTCallsMostPopular;
import com.oconte.david.mynews.Calls.NYTCallsSearch;
import com.oconte.david.mynews.Calls.NYTCallsTopStories;
import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.Models.SearchResult;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
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
        //simuler serveur avec mockwebserver
        NYTService service = Mockito.mock(NYTService.class);

        Call<SearchResult> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.success(null));

        NYTCallsSearch.Callbacks callbacks = Mockito.mock(NYTCallsSearch.Callbacks.class);

        when(service.getSearchSection("01/02/2020", "16/05/2020", "sports", "kobe", 10)).thenReturn(call);

        //NYTCallsSearch.getSearchSection(service,callbacks, "01/02/2020", "16/05/2020", "sports", "kobe", 10);

        verify(callbacks).onResponse(any());

    }

    public void testCallsFOrSearchAndMockServeur() throws Exception {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        MockWebServer server = new MockWebServer();

        // Schedule some responses.
        server.enqueue(new MockResponse().setBody("hello, world!"));
        server.enqueue(new MockResponse().setBody("sup, bra?"));
        server.enqueue(new MockResponse().setBody("yo dog"));

        // Start the server.
        server.start();

        // Ask the server for its URL. You'll need this to make HTTP requests.
        HttpUrl baseUrl = server.url("/v1/chat/");

        // Exercise your application code, which should make those HTTP requests.
        // Responses are returned in the same order that they are enqueued.
        Chat chat = new Chat(baseUrl);

        chat.loadMore();
        assertEquals("hello, world!", chat.messages());

        chat.loadMore();
        chat.loadMore();
        assertEquals(""
                + "hello, world!\n"
                + "sup, bra?\n"
                + "yo dog", chat.messages());

        // Optional: confirm that your app made the HTTP requests you were expecting.
        RecordedRequest request1 = server.takeRequest();
        assertEquals("/v1/chat/messages/", request1.getPath());
        assertNotNull(request1.getHeader("Authorization"));

        RecordedRequest request2 = server.takeRequest();
        assertEquals("/v1/chat/messages/2", request2.getPath());

        RecordedRequest request3 = server.takeRequest();
        assertEquals("/v1/chat/messages/3", request3.getPath());

        // Shut down the server. Instances cannot be reused.
        server.shutdown();
    }

    /*@Test
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

    }

    @Test
    public void testCallsTMostPopular() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);

        Call<Result> call = Mockito.mock(Call.class);

        when(call.execute()).thenReturn(Response.success(null));

        NYTCallsMostPopular.Callbacks callbacks = Mockito.mock(NYTCallsMostPopular.Callbacks.class);

        when(service.getMostPopular( "home")).thenReturn(call);

        NYTCallsMostPopular.getMostPopular(service,callbacks, "home");

        verify(callbacks).onResponse(any());

    }*/

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