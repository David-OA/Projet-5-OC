package com.oconte.david.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSearch {

    @SerializedName("docs")
    @Expose
    private List<Article> docs = null;
    @SerializedName("meta")
    @Expose
    private MetaSearch meta;

    public List<Article> getDocs() {
        return docs;
    }

}
