package com.isaac.springbootjpa.controller;


import com.isaac.springbootjpa.dto.MemberDTO;
import com.isaac.springbootjpa.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JpaController {
    /**
     * 회원 등록 Form 페이지
     * */
    @RequestMapping(value = "/jpa/memberWriteForm", method = RequestMethod.GET)
    public String memberWriteForm(Model model) {
        // 등록 처리(신규회원)
        return "jpa/memberWriteForm";
    }

    /**
     * 회원 등록
     * */
    @RequestMapping(value = "/jpa/memberWriteOk", method = RequestMethod.POST)
    public String insertMember(MemberDTO memberDTO, Model model) {

        try {
            // 등록 처리
            System.out.println(memberDTO.toString());
            // 1. DTO -> Entity 로 변환
            Member member = memberDTO.toEntity();
            System.out.println(member.toString());

            // 2. Repository -> Entity -> DB save


        } catch (Exception e) {
            // err
        }

        return "redirect:/";
    }



    /**
     * 회원 List 페이지
     * */
    @RequestMapping(value = "/jpa/memberList", method = RequestMethod.GET)
    public String memberList(Model model) {
        return "jpa/memberList";
    }

}
