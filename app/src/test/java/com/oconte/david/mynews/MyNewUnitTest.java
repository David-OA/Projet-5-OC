package com.oconte.david.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oconte.david.mynews.Calls.NYTCallsMostPopular;
import com.oconte.david.mynews.Calls.NYTCallsSearch;
import com.oconte.david.mynews.Calls.NYTCallsSports;
import com.oconte.david.mynews.Calls.NYTCallsTopStories;
import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.Search.ResultSearchActivity;
import com.oconte.david.mynews.Utils.ConfigureDate;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
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
    public void testCompareDate() {
        assertTrue(ConfigureDate.compareDate("01/05/2020", "16/05/2020"));
        assertTrue(ConfigureDate.compareDate("", "16/05/2020"));
        assertTrue(ConfigureDate.compareDate("", ""));
    }

    @Test
    public void testCalls() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);
        NYTCallsSearch.Callbacks callbacks = Mockito.mock(NYTCallsSearch.Callbacks.class);

        when(service.getSearchSection("01/02/2020", "16/05/2020", "sports", "kobe", 10)).thenReturn(Response.success(null));

        NYTCallsSearch.getSearchSection(service,callbacks, "01/02/2020", "16/05/2020", "sports", "kobe", 10);

        verify(callbacks).onResponse(any());

    }

    @Test
    public void testFailureCalls() throws IOException {
        NYTService service = Mockito.mock(NYTService.class);
        NYTCallsSearch.Callbacks callbacks = Mockito.mock(NYTCallsSearch.Callbacks.class);

        when(service.getSearchSection("01/02/2020", "16/05/2020", "sports", "kobe", 10)).thenReturn(Response.error(400, ResponseBody.create(MediaType.get("application/json"), "()")));

        NYTCallsSearch.getSearchSection(service,callbacks, "01/02/2020", "16/05/2020", "sports", "kobe", 10);

        verify(callbacks).onFailure();

    }

/*
    @Test
    public void testSearch() {

        assertTrue(NYTCallsSearch.getSearchSection(this, "01/02/2020", "16/05/2020", "sports", "kobe", 10));
        assertFalse(NYTCallsSearch.getSearchSection(this, "01/10/2020", "16/05/2020", "sports", "kobe", 10));
        assertTrue(NYTCallsSearch.getSearchSection(this, "01/02/2020", "16/05/2020", "sports", "kobe", 10));
        assertTrue(NYTCallsSearch.getSearchSection(this, "01/02/2020", "16/05/2020", "sports", "kobe", 10));

    }

    @Test
    private void testStartResultSearchActivity() {

        String query = "kobe";
        String beginDate = "01/02/2020";
        String endDate = "16/05/2020";
        String art = null;
        if (mArt.isChecked()) {
            art = mArt.getText().toString();
        }
        String business = null;
        if (mBusiness.isChecked()) {
            business = mBusiness.getText().toString();
        }
        String entrepreneurs = null;
        if (mEntrepreneurs.isChecked()) {
            entrepreneurs = mEntrepreneurs.getText().toString();
        }
        String politics = null;
        if (mPolitics.isChecked()) {
            politics = mPolitics.getText().toString();
        }
        String sports = null;
        if (mSport.isChecked()) {
            sports = mSport.getText().toString();
        }
        String travel = null;
        if (mTravel.isChecked()) {
            travel = mTravel.getText().toString();
        }

        Intent intent = new Intent(this, ResultSearchActivity.class);
        Bundle searchString = new Bundle();
        searchString.putString("extra_query", query);
        searchString.putString("extra_beginDate", beginDate);
        searchString.putString("extra_endDate", endDate);
        searchString.putString("extra_art", art);
        searchString.putString("extra_business", business);
        searchString.putString("extra_entrepreneurs", entrepreneurs);
        searchString.putString("extra_politics", politics);
        searchString.putString("extra_sports", sports);
        searchString.putString("extra_travel", travel);
        intent.putExtras(searchString);
        startActivity(intent);

    }*/

}