package com.teamProject.syusyu.domain.cs;

import org.springframework.web.util.UriComponentsBuilder;

public class FaqSearchCondition11 {
    private Integer page = 1;   // 페이지
    private Integer pageSize = 10; // 한번에 보여줄 게시물의 갯수
    //    private Integer offset = 0;
    private String keyword = ""; // 검색어

    private String key = ""; // FAQ타입 검색할떄 쓸꺼임
    private String faqTp;

    public FaqSearchCondition11(){}

    public FaqSearchCondition11(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public FaqSearchCondition11(Integer page, Integer pageSize, String keyword, String key,String faqTp) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.key = key;
        this.faqTp = faqTp;
    }

    public String getQueryString(){
        return getQueryString(page);
    }


    public String getQueryString(Integer page) {
        //page?=1&pageSize=10&T&keyword="title"&key=""
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("key", key)
                .queryParam("keyword", keyword)
                .queryParam("faqTp", faqTp)
                .build().toString();
    }

    @Override
    public String toString() {
        return "FaqSearchCondition11{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", keyword='" + keyword + '\'' +
                ", key='" + key + '\'' +
                ", faqTp='" + faqTp + '\'' +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFaqTp() {
        return faqTp;
    }

    public void setFaqTp(String faqTp) {
        this.faqTp = faqTp;
    }
}