package com.oconte.david.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaSearch {

    @SerializedName("hits")
    @Expose
    private Integer hits;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("time")
    @Expose
    private Integer time;

}
