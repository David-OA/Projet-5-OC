package com.oconte.david.mynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment2 extends Fragment implements NYTCalls.Callbacks {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    //@BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    // FOR DATA
    private NYTArticleAdapter adapter;


    Context context;

    Result result;



    public MainFragment2() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();

        //this.executeHttpRequestWithRetrofit();
        //this.executeHttpRequestWithRetrofitSports();
        this.executeHttpRequestWithRetrofitMostPopular();

        //this.executeHttpRequestByTablayout();

        this.configureOnClickRecyclerView();



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager
    private void configureRecyclerView() {

        // Create adapter passing the list of articles
        this.adapter = new NYTArticleAdapter();

        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);

        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -----------------
    // ACTION
    // -----------------

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.activity_web_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        result.articles.get(position);

                        Intent intent = new Intent(getContext(),WebViewActivity.class);
                        intent.putExtra("url", result.articles.get(position).getUrl());

                        startActivity(intent);
                    }
                });
    }


    /*private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }*/

    // -----------------
    // HTTP REQUEST Retrofit
    // -----------------
    private void executeHttpRequestWithRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();
        NYTCalls.getTopStories(this, "movies");
    }

    private void executeHttpRequestWithRetrofitSports() {
        NYTCallsSports.getSports(this, "sports");
    }

    private void executeHttpRequestWithRetrofitMostPopular() {
        NYTCallsMostPopular.getMostPopular(this, "viewed");
    }


    @Override
    public void onResponse(@Nullable Result results) {
        this.result = results;
        this.adapter.updateCallRetrofitNews(results);
    }

    @Override
    public void onFailure() {
        // When getting error, we update UI
        //this.updateUIWhenStopingHTTPRequest("An error happened !");
    }
}
