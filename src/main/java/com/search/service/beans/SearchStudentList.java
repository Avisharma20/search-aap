package com.search.service.beans;

import java.util.List;

public class SearchStudentList {
    List<SearchSpec> searchSpecList;
    List<String> filter;

    public List<SearchSpec> getSearchSpecList() {
        return searchSpecList;
    }

    public void setSearchSpecList(List<SearchSpec> searchSpecList) {
        this.searchSpecList = searchSpecList;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }
}
