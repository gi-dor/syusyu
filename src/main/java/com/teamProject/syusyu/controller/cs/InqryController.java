package com.teamProject.syusyu.controller.cs;

import com.teamProject.syusyu.common.ViewPath;
import com.teamProject.syusyu.domain.BoardDto;
import com.teamProject.syusyu.domain.PageHandler;
import com.teamProject.syusyu.domain.PageHandler2;
import com.teamProject.syusyu.domain.SearchCondition;
import com.teamProject.syusyu.domain.cs.InqryDTO;
import com.teamProject.syusyu.service.BoardService;
import com.teamProject.syusyu.service.cs.InqryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inqry")
public class InqryController {

        @Autowired
        InqryService inqryService;

        @PostMapping("/remove")
        public String remove(Integer inqryNo, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
            InqryDTO inqry = new InqryDTO();
            Integer mbrId = (Integer)session.getAttribute("mbrId");
            // 세션에서 regrId 값 가져오기
            try {
                inqryService.remove(inqryNo, mbrId.toString());
            } catch (Exception e) {
                e.printStackTrace();
//                rattr.addFlashAttribute("msg", "DEL_ERR");
            }

            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            return ViewPath.FOS_MYPAGE +"inqryList";
            //"redirect:" +

        }

    @PostMapping("/inqry")
    public String inqry(Integer inqryNo, Model m) {
        try{
            InqryDTO inqryDTO = inqryService.read(inqryNo);
            m.addAttribute(inqryDTO);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return ViewPath.FOS_MYPAGE +"inqry";
    }

    @GetMapping("/read")
        public String read(Integer inqryNo, Model m) {
            try{
                InqryDTO inqryDTO = inqryService.read(inqryNo);
                m.addAttribute(inqryDTO);
            }catch (Exception e) {
                e.printStackTrace();
            }

            return ViewPath.FOS_MYPAGE +"inqry";
        }



        @GetMapping("/inqryList")
        public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
//            if(!loginCheck(request))
//                return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

            if(page==null) page=1;
            if(pageSize==null) pageSize=10;

            try {
                int totalCnt = inqryService.getCount();
                PageHandler2 pageHandler = new PageHandler2(totalCnt, page, pageSize);

                Map map = new HashMap();
                map.put("offset", (page-1)*pageSize);
                map.put("pageSize", pageSize);

                List<InqryDTO> list = inqryService.getPage(map);
                m.addAttribute("list", list);
                m.addAttribute("ph", pageHandler);
                m.addAttribute("page", page);
                m.addAttribute("pageSize", pageSize);
                // 모델에 inqry 테이블의 컬럼 개수 추가
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ViewPath.FOS_MYPAGE +"inqryList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
        }

        @PostMapping("/modify")
        public String modify(InqryDTO inqryDTO, Model m, HttpSession session, RedirectAttributes rattr){
            InqryDTO inqry = new InqryDTO();
            Integer mbrId = (Integer) session.getAttribute("mbrId"); // 세션에서 regrId 값 가져오기

            try {
                int rowCnt = inqryService.modify(inqryDTO);

                if(rowCnt!=1)
                    throw new Exception("Modify failed");

                rattr.addFlashAttribute("msg", "MOD_OK");

                return ViewPath.FOS_MYPAGE +"inqrymodify";
//                원래 return문
//                return "redirect:/inqry/inqryList";
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute(inqryDTO);
                m.addAttribute("msg", "MOD_ERR");
                return ViewPath.FOS_MYPAGE +"inqrymodify";
            }
        }
//
//
//        @PostMapping("/write")
//        public String write(InqryDTO inqryDTO, Model m, HttpSession session, RedirectAttributes rattr){
//            String writer = (String) session.getAttribute("id");
//            inqryDTO.setWriter(writer);
//
//            try {
//                int rowCnt = inqryService.write(inqryDTO);
//
//                if(rowCnt!=1)
//                    throw new Exception("Write failed");
//
//                rattr.addFlashAttribute("msg", "WRT_OK");
//
//                return ViewPath.FOS_MYPAGE +"inqryList";
//            } catch (Exception e) {
//                e.printStackTrace();
//                m.addAttribute(inqryDTO);
//                m.addAttribute("msg", "WRT_ERR");
//                return "board";
//            }
//        }
//
//        @GetMapping("/write")
//        public String write(Model m) {
//            m.addAttribute("mode", "new");
//            return "board";
//        }
//

}