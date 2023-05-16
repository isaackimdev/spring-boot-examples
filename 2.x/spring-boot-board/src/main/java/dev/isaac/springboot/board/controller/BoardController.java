package dev.isaac.springboot.board.controller;

import dev.isaac.springboot.board.dtos.BoardDto;
import dev.isaac.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        boardService.deleteById(id);
        return "deleted";
    }

    // Go to the bulletin board registration form
    @RequestMapping(value="/boardForm", method = RequestMethod.GET)
    public String goToBoardForm(Model model,
                                @RequestParam(name = "boardId", defaultValue = "", required = false) String boardId,
                                @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("board", boardId.equals("") ? BoardDto.builder().build() : boardService.findById(Long.parseLong(boardId)));
        model.addAttribute("pageable", pageable);
        return "boardForm";
    }

    // Create/Modify - Post board
    // Post - Patch or Put 으로 처리도 가능하나 board example 정도여서 POST 내에서 분기 처리함.
    @ResponseBody
    @RequestMapping(value="/board", method = RequestMethod.POST)
    public Map<String, Object> createBoard(@RequestBody BoardDto.PostRequest boardPostRequest,
                                           @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Map <String, Object> map = new HashMap<>();
        BoardDto savedBoard = null;
        if (boardPostRequest.getId() == null) {
            savedBoard = boardService.createBoard(boardPostRequest);
        } else {
            savedBoard = boardService.modifyBoard(boardPostRequest);
        }
        map.put("data", savedBoard);
        map.put("result", savedBoard == null ? "failed" : "succeed");
        return map;
    }

}
