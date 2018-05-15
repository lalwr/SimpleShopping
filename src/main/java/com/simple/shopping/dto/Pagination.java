package com.simple.shopping.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private String searchStr = "";
    private String searchType = "";
    private long totalCount;
    private long totalPage;
    private Integer curPage=1;
    private Integer countPerPage = 5;
    private Integer buttonCount = 6;

    public Pagination(Integer curPage, String searchType, String searchStr){
        this.curPage = curPage;
        this.searchType = searchType;
        this.searchStr = searchStr;
    }

    public int getStartPage(){
        return ( ( curPage / buttonCount - ( ( curPage % buttonCount == 0 )? 1 : 0 ) ) * buttonCount ) + 1;
    }
    public int getEndPage(){
        return ( curPage / buttonCount + 1 - ( ( curPage % buttonCount == 0 )? 1 : 0 ) ) * buttonCount;
    }
    public int getPrevPage(){
        return ( (curPage / buttonCount) - 1 - ( ( curPage % buttonCount == 0 ) ? 1 : 0 ) ) * buttonCount + 1;
    }
    public int getNextPage() {
        return ((curPage / buttonCount) + 1 - ((curPage % buttonCount == 0) ? 1 : 0)) * buttonCount + 1;
    }
}
