package com.teamProject.syusyu.controller.fos.member;

import com.teamProject.syusyu.common.ViewPath;
import com.teamProject.syusyu.domain.member.MemberDTO;
import com.teamProject.syusyu.service.fos.member.FOS_MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(ViewPath.FOS)
public class FOS_LoginController {
    @Autowired
    FOS_MemberService memberService;


    @GetMapping("/login")
    public String loginForm(HttpServletRequest request) {
        // 이전 페이지 URL 저장
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        if (referer != null) {
            session.setAttribute("prevPage", referer);
        }

        return ViewPath.FOS_MEMBER + "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO, HttpServletRequest request) {
        MemberDTO checkedMember;
        try {
            // 1. id와 pwd를 확인한다.
            checkedMember = memberService.loginCheck(memberDTO);
            if (checkedMember == null) {
                throw new Exception("잘못된 아이디 또는 비밀번호");
            }
        } catch (Exception e) {
            // 로그인 확인 중 예외 발생
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        //2. id와 pwd가 일치하면 세션에 id를 저장한다.
        HttpSession session = request.getSession();
        session.setAttribute("mbrId", checkedMember.getMbrId());

        //3. 이전 페이지 URL 가져오기
        String prevPage = (String) session.getAttribute("prevPage");

        //4. 클라이언트에게 성공 메시지와 이전 페이지 URL을 보낸다.
        return new ResponseEntity<>(prevPage, HttpStatus.OK);
    }
}