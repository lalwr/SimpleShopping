package com.simple.shopping.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private String searchStr;
    private String searchType;
    private long totalCount;
    private long totalPage;
    private Integer curPage=1;
    private Integer countPerPage = 3;
    private Integer buttonCount = 6;


    public int getStartPage(){
        return ( curPage / buttonCount * buttonCount ) + 1 - ( ( curPage % buttonCount == 0 )? 1 : 0 );
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
