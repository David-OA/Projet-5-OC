package com.oconte.david.mynews.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oconte.david.mynews.Models.Article;
import com.oconte.david.mynews.Models.ResponseSearch;
import com.oconte.david.mynews.Models.SearchResult;
import com.oconte.david.mynews.R;

import static android.content.Context.MODE_PRIVATE;

public class NYTResultSearchAdapter extends RecyclerView.Adapter<NYTArticleSearchViewHolder> {

    private SearchResult results = new SearchResult();
    private String url = "extra_url";

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

        Article article = results.getResponse().getDocs().get(viewHolder.getAdapterPosition());

        url = article.getUrl();

        SharedPreferences preferencesUrl = viewHolder.itemView.getContext().getSharedPreferences("UrlPrefs", MODE_PRIVATE);
        String urls = preferencesUrl.getString("EXTRA_URL", "");
        if (urls.contains(url)) {
            viewHolder.textView.setTextColor(Color.parseColor("#96ff01"));
            viewHolder.textView1.setTextColor(Color.parseColor("#96ff01"));
            viewHolder.date.setTextColor(Color.parseColor("#96ff01"));
        } else {
            viewHolder.textView.setTextColor(Color.parseColor("#000000"));
            viewHolder.textView1.setTextColor(Color.parseColor("#000000"));
            viewHolder.date.setTextColor(Color.parseColor("#000000"));
        }

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
