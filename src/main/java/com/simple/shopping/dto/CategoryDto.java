package com.simple.shopping.dto;

import java.util.List;

public class CategoryDto {
    private Long no;
    private String name;

    List<CategoryDto> ctgrs;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDto> getCtgrs() {
        return ctgrs;
    }

    public void setCtgrs(List<CategoryDto> ctgrs) {
        this.ctgrs = ctgrs;
    }
}
