package com.oconte.david.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    @SerializedName(value = "section", alternate = "section_name")
    @Expose
    private String section;

    @SerializedName(value = "subsection", alternate = "subsection_name")
    @Expose
    private String subsection;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("abstract")
    @Expose
    private String _abstract;

    @SerializedName(value = "url", alternate = "web_url")
    @Expose
    private String url;

    @SerializedName("item_type")
    @Expose
    private String itemType;

    @SerializedName("updated_date")
    @Expose
    private String updatedDate;

    @SerializedName("created_date")
    @Expose
    private String createdDate;

    @SerializedName(value = "published_date", alternate = "pub_date")
    @Expose
    private String publishedDate;

    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;

    @SerializedName("kicker")
    @Expose
    private String kicker;

    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia = null;

    @SerializedName("short_url")
    @Expose
    private String shortUrl;

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    ////////////////////////////////////////////////////
    // FOR TOPSTORIES
    ////////////////////////////////////////////////////

    @SerializedName("media")
    @Expose
    private List<Medium> medium = null;

    public List<Medium> getMedium() {
        return medium;

    }

    ///////////////////////////////////////////////////
    // FOR SEARCH
    ///////////////////////////////////////////////////

    @SerializedName("headline")
    @Expose
    private HeadlineSearch headline;

    @SerializedName("response")
    @Expose
    private ResponseSearch response;

    public ResponseSearch getResponse() {
        return response;
    }

    public void setResponse(ResponseSearch response) {
        this.response = response;
    }

    public HeadlineSearch getHeadline() {
        return headline;
    }

}
