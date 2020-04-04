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

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
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

    public void setMedia(List<Medium> media) {
        this.medium = medium;

    }

    ///////////////////////////////////////////////////
    // FOR SEARCH
    ///////////////////////////////////

    @SerializedName("snippet")
    @Expose
    private String snippet;

    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;

    @SerializedName("blog")
    @Expose
    private Object blog;

    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("headline")
    @Expose
    private HeadlineSearch headline;

    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = null;


    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;


    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("word_count")
    @Expose
    private Integer wordCount;

    @SerializedName("score")
    @Expose
    private Integer score;

    @SerializedName("uri")
    @Expose
    private String uri;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("copyright")
    @Expose
    private String copyright;

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

    public void setHeadline(HeadlineSearch headline) {
        this.headline = headline;
    }

}
