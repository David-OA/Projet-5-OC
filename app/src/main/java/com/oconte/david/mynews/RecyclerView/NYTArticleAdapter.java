package com.oconte.david.mynews.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.oconte.david.mynews.Models.Article;
import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NYTArticleAdapter extends RecyclerView.Adapter<NYTArticleViewHolder> {

    private Result results = new Result();

    Context context;

    int row_index = -1;

    private SharedPreferences preferencesUrl;
    private String url = "extra_url";

    /*public NYTArticleAdapter(Result results, Context context) {
        this.results = results;
        this.context = context;
    }*/

    public NYTArticleAdapter() {
    }

    @NonNull
    @Override
    public NYTArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false );

        return new NYTArticleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NYTArticleViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        viewHolder.updateWithNYTArticle(this.results.articles.get(position));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }

        });

        Article article = results.articles.get(viewHolder.getAdapterPosition());

        url = article.getUrl();

        SharedPreferences preferencesUrl = viewHolder.itemView.getContext().getSharedPreferences("UrlPrefs", MODE_PRIVATE);
        String urls = preferencesUrl.getString("EXTRA_URL", "");
        if (urls.contains(url)) {
            viewHolder.textView.setTextColor(Color.parseColor("#96ff01"));
            viewHolder.textView1.setTextColor(Color.parseColor("#96ff01"));
            viewHolder.date.setTextColor(Color.parseColor("#000000"));
        } else {
            viewHolder.textView.setTextColor(Color.parseColor("#000000"));
            viewHolder.textView1.setTextColor(Color.parseColor("#000000"));
            viewHolder.date.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return this.results.articles.size();
    }

    public void updateCallRetrofitNews(Result results) {
        this.results = results;
        this.notifyDataSetChanged();
    }
}
