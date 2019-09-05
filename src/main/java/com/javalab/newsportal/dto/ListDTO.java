package com.javalab.newsportal.dto;

import java.util.List;

public class ListDTO {

    private List<Long> ids;

    public ListDTO(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}