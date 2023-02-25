package com.isaac.springbootthymeleaf.contoller;

import com.isaac.springbootthymeleaf.dto.ExamDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello"; // "hello", "/hello", "hello.html"
    }

    // Model(data) test
    @RequestMapping("/data-test")
    public String dataTest(Model model) { // Model 선언
        // Data 세팅
        model.addAttribute("data1", "대한민국");
        return "data-test";
    }

    // ModelAndView test
    @RequestMapping("/mv-test")
    public ModelAndView modelAndViewTest(ModelAndView modelAndView) { // Model(Data) + View 지정

        // (1) 데이터 지정
        // (2) 뷰페이지 지정
        // (3) return
        modelAndView.addObject("name", "이순신");
        modelAndView.addObject("age", 29);
        modelAndView.setViewName("mv-test");
        return modelAndView;
    }

    @RequestMapping("/utext")
    public String utext(Model model) {
        model.addAttribute("tag","<h2>태그 전달하기</h2>");
        return "utext";
    }


    @RequestMapping("/pv/{num}")
    public String pv1(Model model, @PathVariable(name = "num") int numtest) {
        model.addAttribute("num", numtest);
        System.out.println("test : " +numtest);
        return "pv1";
    }

    @RequestMapping("/pv-mav/{num}")
    public ModelAndView pv1mav(ModelAndView mav, @PathVariable(name = "num") int numtest) {
        mav.addObject("num", numtest);
        mav.setViewName("pv1");
        System.out.println("test : " +numtest);
        return mav;
    }

    // Form
    @RequestMapping(value = "/form1", method = RequestMethod.GET)
    public String form1(Model model) {
        return "form1";
    }

    @RequestMapping(value = "/form1", method = RequestMethod.POST)
    public String form2(Model model, @RequestParam("data1") String data1) {
        model.addAttribute("data1", data1);
        return "form1";
    }

    @RequestMapping(value = "/multi1", method = RequestMethod.GET)
    public ModelAndView multiFormPage(ModelAndView mav) {
        mav.addObject("msg", "여러 개 input 값 입력 후 전송 버튼 클릭!");
        mav.setViewName("multi1");
        return mav;
    }

    @RequestMapping(value ="/multi1", method = RequestMethod.POST)
    public ModelAndView multiFormSend(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("age") Integer age,
            @RequestParam("gender") String gender,
            ModelAndView mav) {
        mav.addObject("id", id);
        mav.addObject("name", name);
        mav.addObject("email", email);
        mav.addObject("age", age);
        mav.addObject("gender", gender);
        mav.setViewName("multi1");
        return mav;
    }

    // dto get
    @RequestMapping(value = "/multi-dto", method = RequestMethod.GET)
    public ModelAndView multiFormPage2(
            @ModelAttribute("formData") ExamDto examDto,
            ModelAndView mav) {
        mav.addObject("msg","get req");
        mav.addObject("formData", examDto);
        mav.setViewName("multi-dto");
        return mav;
    }

    // dto post
    @RequestMapping(value = "/multi-dto", method = RequestMethod.POST)
    public ModelAndView multiFormDtoSend(
            @ModelAttribute("formData") ExamDto examDto,
            ModelAndView mav) {
        mav.addObject("formData", examDto);
        mav.setViewName("multi-dto");
        return mav;
    }

    // Thymeleaf strings util methods
    @RequestMapping(value = "/string-util", method = RequestMethod.GET)
    public ModelAndView viewStringUtil(ModelAndView mav) {
        mav.addObject("msg", "Hello world");
        mav.setViewName("string-util");
        return mav;
    }

    @RequestMapping(value = "/th-block", method = RequestMethod.GET)
    public ModelAndView viewThBlock(ModelAndView mav) {
        mav.addObject("account","회원");
        mav.setViewName("th-block");
        return mav;
    }
}
