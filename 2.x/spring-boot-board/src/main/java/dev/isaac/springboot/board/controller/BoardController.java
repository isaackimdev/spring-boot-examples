package dev.isaac.springboot.board.controller;

import dev.isaac.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // Read all
    @RequestMapping(value = "/boardList", method = RequestMethod.GET)
    public String boardList(Model model,
                            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boardList", boardService.findAll(pageable));
        return "boardList";
    }

    // Read
    @RequestMapping(value = "/board/{id}", method = RequestMethod.GET)
    public String getBoard(Model model,
                           @PathVariable(name = "id") Long id,
                           @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("pageable", pageable);
        model.addAttribute("board", boardService.findById(id));
        return "board";
    }

    // Delete
    @ResponseBody // REST
    @RequestMapping(value="/board/{id}", method = RequestMethod.DELETE)
    public String deleteBoard(Model model,
                              @PathVariable(name = "id") Long id,
                              @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("/board-delete/" + id);
        return "";
    }



}
