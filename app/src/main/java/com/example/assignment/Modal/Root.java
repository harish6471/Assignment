package com.example.assignment.Modal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Root {


    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<Datum> data = new ArrayList<Datum>();
    private Support support;
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPerPage() {
        return perPage;
    }
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    public List<Datum> getData() {
        return data;
    }
    public void setData(List<Datum> data) {
        this.data = data;
    }
    public Support getSupport() {
        return support;
    }
    public void setSupport(Support support) {
        this.support = support;
    }
}