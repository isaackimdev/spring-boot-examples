package com.isaac.springbootjpa.controller;


import com.isaac.springbootjpa.dto.MemberDTO;
import com.isaac.springbootjpa.entity.Member;
import com.isaac.springbootjpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public String memberList(Model model, Pageable pageable) {
        // JPA 전체
        // List<Member> members = memberRepository.findAll();

        // JPA + Paging -> Page, Pageable -> Spring Data JPA
        // require
        // Paging
        //      import org.springframework.data.domain.Page;
        //      import org.springframework.data.domain.Pageable;
        // Sort
        //      import org.springframework.data.domain.Sort;
        Page<Member> members = memberRepository.findAll( pageable );
        /*
        * sub description
        * Pageable 객체를 활용한 Pagination(Paging) 구현
        *   0. 페이징 구현을 위해 수정이 필요한 파일 -> JpaController.java, memberList.html
        *   1. 페이징 구현 시 필요한 메서드들을 알아서 제공 -> 사용자는 메서드별 용도를 잘 이해하고 적절히 사용해서 구현하면 됨.
        *   2. 페이징은 개발자가 직접 구현하는 것도 가능하나 프레임워크 쓰는 이유와도 상충 -> 있는 거 빨리 적용해서 구현.
        *   3. 기본적인 사용법 -> query Parameter 로 page(몇 페이지?), size(default : 20 - page 내 글 개수), sort
        *   4. 뷰페이지 구현 시 해당 페이지에서 뭘 사용하고 어떻게 조건 처리를 해야하는지 생각해야 하므로 더 까다롭게 느낄 수 있음.
        *   5. 첫 페이지 = 0 부터 시작, 뷰페이지에서 Previous / Next 구현 시 적절히 +1, -1 한다.
        *   6. 자주 사용하는 필수적인 페이징 관련 메서드 암기
        *   7. thymeleaf view 페이지에서 처리 (반복, 숙달, 연습)
        *   8. 뷰페이지에서 적용할 값을 messages.properties 파일에 정의하여 사용. 예) 한 페이지에 보여질 size 값 등.
        * */

        // 페이징 관련 유용한 메서드들
        System.out.println(members.getTotalPages());    // 총 페이지 수
        System.out.println(members.getTotalElements()); // 총 레코드의 개수
        System.out.println(members.getNumber());        // 현재 페이지 번호
        System.out.println(members.getSize());          // 한 페이지에 보여지는 글(레코드)의 개수
        System.out.println(members.getSort());          // Option
        System.out.println(members.getPageable());      // number, size, sort 이 3가지 값을 모두 가지고 있음

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


    /**
     * 회원 상세 페이지
     * 
     */
    @RequestMapping(value = "/jpa/memberDetail", method = RequestMethod.GET)
    public String memberDetail(
            @RequestParam(value = "num", required = false) Integer num,
            Model model
    ) {
        System.out.println(num);

        Member member = memberRepository.findById(num).orElse(null);
        model.addAttribute("member", member);

        return "jpa/memberDetail";
    }

}
