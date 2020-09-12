package com.oconte.david.mynews;

import androidx.annotation.Nullable;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.oconte.david.mynews.calls.NYTCallsSearch;
import com.oconte.david.mynews.di.Injection;
import com.oconte.david.mynews.models.SearchResult;
import com.oconte.david.mynews.utils.ConfigureDate;
import com.oconte.david.mynews.utils.ConfigureText;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;

import javax.crypto.spec.PSource;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static retrofit2.converter.gson.GsonConverterFactory.create;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyNewUnitTest  {
    
    private SearchResult result;
    private String baseUrl = "http://127.0.0.1:9900";

    // ue metheode permettant dexternaliser la creation de lobjet mockwebserver en lui passant le bon code http(200, 400 ou autre) et la bonne reponse json
    private MockWebServer setupServer(int code, String response) {
        MockWebServer server = new MockWebServer();
        server.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                return new MockResponse().setResponseCode(code).setBody(response);
            }
        });
        return server;
    }

    @Test
    public void testCallsForSearch() throws IOException, InterruptedException {

        MockWebServer server = setupServer(HttpURLConnection.HTTP_OK, Const.SEARCH_RESPONSE);

        final CountDownLatch latch = new CountDownLatch(1);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(create())
                .build();
        
        // Start the server.
        server.start(9900);

        //Simulation with Mockwebserver
        NYTService service = retrofit.create(NYTService.class);

        NYTCallsSearch.Callbacks callbacks = new NYTCallsSearch.Callbacks() {
            @Override
            public void onResponse(@Nullable SearchResult response) {
                result = response;
                latch.countDown();
            }

            @Override
            public void onFailure() {
                result = null;
                latch.countDown();
            }
        };

        CountingIdlingResource resource = Injection.getCounting();

        NYTCallsSearch search = new NYTCallsSearch(service, resource);
        search.getSearchSection(service, callbacks, "01/02/2020", "16/05/2020", "sports", "kobe", 10);

        latch.await();

        assertNotNull(result);

    }

    @Test
    public void testCompareDate() {

        assertTrue (ConfigureDate.compareDate("06/05/2020", "09/05/2020"));
        assertTrue (ConfigureDate.compareDate("", "09/05/2020"));
        assertTrue (ConfigureDate.compareDate("06/05/2020", ""));
        assertTrue (ConfigureDate.compareDate("06/05/2020", "06/05/2020"));

    }

    @Test
    public void testConfigureText() {

        String expected = "section >subsection";
        String section = "section;subsection";

        assertEquals(expected, ConfigureText.convertSectionNameForDisplay(section));

    }

    @Test
    public void testConfigureTextForNoSubsection() {

        String expected = "";
        String section = null;

        assertEquals(expected, ConfigureText.convertSectionNameForDisplay(section));

    }

}