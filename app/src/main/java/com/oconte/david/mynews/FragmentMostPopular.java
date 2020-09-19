package com.oconte.david.mynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oconte.david.mynews.calls.NYTCallsMostPopular;
import com.oconte.david.mynews.models.Result;
import com.oconte.david.mynews.recyclerView.NYTArticleAdapter;
import com.oconte.david.mynews.webView.ItemClickSupport;
import com.oconte.david.mynews.webView.WebViewActivity;
import com.oconte.david.mynews.di.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMostPopular extends Fragment implements NYTCallsMostPopular.Callbacks{

    // FOR DESIGN
    @BindView(R.id.fragment_main_mostpopular_recycler_view) RecyclerView recyclerView;

    // FOR DATA
    private NYTArticleAdapter adapter;
    Context context;
    Result result;

    public FragmentMostPopular() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mostpopular, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();

        this.executeHttpRequestWithRetrofitMostPopular();

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

    // -----------------
    // HTTP REQUEST Retrofit
    // -----------------

    private void executeHttpRequestWithRetrofitMostPopular() {
        NYTCallsMostPopular mostPopular = Injection.getMostPopular(Injection.getService(), Injection.getCounting());
        mostPopular.getMostPopular(this, "viewed");
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
