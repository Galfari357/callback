package com.ari.model.artikel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("news_id")
    @Expose
    private String newsId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("like_count")
    @Expose
    private String likeCount;
    @SerializedName("is_headline")
    @Expose
    private String isHeadline;
    @SerializedName("comment_count")
    @Expose
    private String commentCount;
    @SerializedName("is_liked")
    @Expose
    private String isLiked;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getIsHeadline() {
        return isHeadline;
    }

    public void setIsHeadline(String isHeadline) {
        this.isHeadline = isHeadline;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(String isLiked) {
        this.isLiked = isLiked;
    }
}
