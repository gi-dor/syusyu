package com.teamProject.syusyu.service.cs;

import com.teamProject.syusyu.domain.cs.NoticeDTO;
import com.teamProject.syusyu.domain.cs.SearchCondition;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    int getCount() throws Exception;

    int remove(Integer notcNo, Integer regrId) throws Exception;

    int write(NoticeDTO noticeDto) throws Exception;

    List<NoticeDTO> getList() throws Exception;

    NoticeDTO read(Integer notcNo) throws Exception;


    List<NoticeDTO> getPage(Map map) throws Exception;

    int modify(NoticeDTO noticeDto) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    List<NoticeDTO> getSearchResultPage(SearchCondition sc) throws Exception;

    NoticeDTO getPrevTitle(Integer notcNo) throws Exception;

    NoticeDTO getNextTitle(Integer notcNo) throws Exception;
}
