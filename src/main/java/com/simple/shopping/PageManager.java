package com.simple.shopping;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageManager{
    // 한번에 화면에 출력되는 상품 수, 현재 페이지, 총 페이지 수, 앞으로 가기, 뒤로가기, 맨 앞으로 가기, 맨 뒤로 가기
    private int currentPage;
    private int pageOffset;
    private int totalPage;
    private String search;
    private String category;
    public static final int PAGE_SCOPE = 5;

    public void setCurrentPage(int page) {
        this.currentPage = page % PAGE_SCOPE;
    }

    public void setPageOffset(int page) {
        this.pageOffset = (page-1)/PAGE_SCOPE *PAGE_SCOPE;
    }

    public void setTotalPage(int totalProducts){
        this.totalPage = totalProducts/PAGE_SCOPE + 1;
    }

}
