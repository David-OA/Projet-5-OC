package com.oconte.david.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("results")
    @Expose
    public List<Article> articles = new ArrayList<>();
}
