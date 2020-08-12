package com.oconte.david.mynews.Search;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.oconte.david.mynews.Calls.NYTCallsSearch;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.NYTFactory;
import com.oconte.david.mynews.NYTService;
import com.oconte.david.mynews.R;
import com.oconte.david.mynews.RecyclerView.NYTResultSearchAdapter;
import com.oconte.david.mynews.Utils.ConfigureDate;
import com.oconte.david.mynews.WebView.ItemClickSupport;
import com.oconte.david.mynews.WebView.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class ResultSearchActivity extends AppCompatActivity implements NYTCallsSearch.Callbacks {

    // FOR DESIGN
    @BindView(R.id.result_search_view) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    // FOR DATA
    private NYTResultSearchAdapter adapter;
    private SearchResult result;

    // -----------------
    // GET DATA FROM INTENT
    // -----------------
    String query = "";
    String beginDate = "";
    String endDate = "";
    String art;
    String business;
    String entrepreneurs;
    String politics;
    String sports;
    String travel;
    String sectionTerm;
    String correctBeginDate;
    String correctendDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        this.configureRecyclerView();
        this.executeHttpRequestWithRetrofit();
        this.configureOnClickRecyclerView();
        this.configureToolbar();
        this.getSearchQuery();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void getSearchQuery() {
        Bundle searchString = getIntent().getExtras();
        assert searchString != null;

        query = searchString.getString("extra_query");
        beginDate = searchString.getString("extra_beginDate");
        if (beginDate != null){
            correctBeginDate = ConfigureDate.convertDateForAPI(beginDate);
        }

        endDate = searchString.getString("extra_endDate");
        if (endDate != null){
            correctendDate = ConfigureDate.convertDateForAPI(endDate);
        }

        art = searchString.getString("extra_art");
        if (art != null) {
            sectionTerm = sectionTerm + "," + art;
        }

        business = searchString.getString("extra_business");
        if (business != null) {
            sectionTerm = sectionTerm + "," + business;
        }

        entrepreneurs = searchString.getString("extra_entrepreneurs");
        if (entrepreneurs != null) {
            sectionTerm = sectionTerm + "," + entrepreneurs;
        }

        politics = searchString.getString("extra_politics");
        if (politics != null) {
            sectionTerm = sectionTerm + "," + politics;
        }

        sports = searchString.getString("extra_sports");
        if (sports != null) {
            sectionTerm = sectionTerm + "," + sports;
        }

        travel = searchString.getString("extra_travel");
        if (travel != null) {
            sectionTerm = sectionTerm + "," + travel;
        }

    }

    // Configure RecyclerView, Adapter, LayoutManager
    private void configureRecyclerView() {

        // Create adapter passing the list of articles
        this.adapter = new NYTResultSearchAdapter();

        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);

        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.activity_web_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        result.getResponse().getDocs().get(position);

                        Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
                        intent.putExtra("url", result.getResponse().getDocs().get(position).getUrl());

                        startActivity(intent);
                    }
                });
    }

    private void executeHttpRequestWithRetrofit() {
        getSearchQuery();
        NYTCallsSearch.getSearchSection(NYTFactory.getRetrofit().create(NYTService.class),this, correctBeginDate, correctendDate, sectionTerm, query, 10);
    }

    @Override
    public void onResponse(@Nullable SearchResult response) {
        if (response == null || response.getResponse().getDocs().size() == 0){
            noMoreNew();
        } else {
            this.result = response;
            this.adapter.updateCallRetrofitNews(response);
        }
    }

    @Override
    public void onFailure() {
        // When getting error, we update UI
        Log.d("failure", "onFailure");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * It's for ERROR MESSAGE
     */
    public void noMoreNew() {
        AlertDialog.Builder myAlertDialogue = new AlertDialog.Builder(this);
        myAlertDialogue.setTitle("Alert ! ");
        myAlertDialogue.setMessage("No more News");

        myAlertDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myAlertDialogue.show();
    }
}
