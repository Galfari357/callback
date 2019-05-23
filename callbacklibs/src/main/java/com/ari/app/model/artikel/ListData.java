package com.ari.app.model.artikel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListData {

    @SerializedName("list")
    @Expose
    private ListNewsData list;
    @SerializedName("page_size")
    @Expose
    private String pageSize;
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("page")
    @Expose
    private Integer page;

    public ListNewsData getList() {
        return list;
    }

    public void setList(ListNewsData list) {
        this.list = list;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
