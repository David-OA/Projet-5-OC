package com.oconte.david.mynews.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oconte.david.mynews.Models.ResponseSearch;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.R;

public class NYTResultSearchAdapter extends RecyclerView.Adapter<NYTArticleSearchViewHolder> {

    private SearchResult results = new SearchResult();

    public NYTResultSearchAdapter() {
    }

    @NonNull
    @Override
    public NYTArticleSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false );

        return new NYTArticleSearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NYTArticleSearchViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticle(this.results.getResponse().getDocs().get(position));

    }

    @Override
    public int getItemCount() {
        ResponseSearch response = this.results.getResponse();
        if (response == null) {
            return 0;
        }
        return response.getDocs().size();
    }

    public void updateCallRetrofitNews(SearchResult results) {
        this.results = results;
        this.notifyDataSetChanged();
    }



}
