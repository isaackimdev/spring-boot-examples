package com.isaac.springbootjpa.controller;


import com.isaac.springbootjpa.dto.MemberDTO;
import com.isaac.springbootjpa.entity.Member;
import com.isaac.springbootjpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JpaController {

    /**
     * DI
     * */
    @Autowired // 고전
    private MemberRepository memberRepository;


    /**
     * 회원 등록 Form 페이지
     * 회원 등록 + 회원 수정
     * */
    @RequestMapping(value = "/jpa/memberWrite", method = RequestMethod.GET)
    public String memberWriteForm(
            @RequestParam(value = "num", required = false) Integer num,
            Model model) {

        if ( num != null ) {
            System.out.println(num);

            // 기존 회원 수정
            Member member = memberRepository.findById(num).orElse(null);
            model.addAttribute("memberDTO", member);
            model.addAttribute("formTitle", "Modification");
        } else {
            // 신규 회원 등록
            model.addAttribute("memberDTO", new MemberDTO());
            model.addAttribute("formTitle", "Registration");
        }

        return "jpa/memberWriteForm";
    }

    /**
     * 회원 등록
     * */
    @RequestMapping(value = "/jpa/memberWrite", method = RequestMethod.POST)
    public String insertMember(MemberDTO memberDTO, Model model) {

        try {
            // 등록 처리
            System.out.println(memberDTO.toString());
            // 1. DTO -> Entity 로 변환
            Member member = memberDTO.toEntity();
            System.out.println(member.toString());

            // 2. Repository -> Entity -> DB save
            // memberRepository.save(member);
            Member savedMemeber = memberRepository.save(member); // save() -> sql -> insert, update
            System.out.println( savedMemeber.toString() );

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
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "jpa/memberList";
    }

    /**
     * 회원 삭제
     * */
    @RequestMapping(value = "/jpa/memberDelete", method = RequestMethod.GET)
    public String memberDelete(@RequestParam(value = "num", required = false) Integer num) {
        System.out.println(num);

        memberRepository.deleteById(num);

        return "redirect:/";
    }


}
