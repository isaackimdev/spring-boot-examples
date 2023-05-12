package dev.isaac.springboot.board.controller;

import dev.isaac.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @RequestMapping(value = "/boardList", method = RequestMethod.GET)
    public String boardList(Model model,
                            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boardList", boardService.findAll(pageable));
        return "boardList";
    }

}
