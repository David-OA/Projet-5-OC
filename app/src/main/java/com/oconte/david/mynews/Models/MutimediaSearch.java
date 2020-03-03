package com.oconte.david.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MutimediaSearch {

    @SerializedName("rank")
    @Expose
    private long rank;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("height")
    @Expose
    private long height;
    @SerializedName("width")
    @Expose
    private long width;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("legacy")
    @Expose
    private Object legacy;

    public String getUrl() {
        return "https://www.nytimes.com/" + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
