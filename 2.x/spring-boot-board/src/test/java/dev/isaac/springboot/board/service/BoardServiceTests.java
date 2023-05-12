package dev.isaac.springboot.board.service;

import dev.isaac.springboot.board.dtos.BoardDto;
import dev.isaac.springboot.board.entities.BoardEntity;
import dev.isaac.springboot.board.repositories.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class BoardServiceTests {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    // READ ALL - service code
    @Test
    @DisplayName("findAllPageableTest")
    public void findAllPageableTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<BoardEntity> boardEntities = boardRepository.findAll(pageable);
        List<BoardDto> boardDtos = boardEntities.getContent().stream().map(BoardDto::fromEntity).collect(Collectors.toList());
        Page<BoardDto> boardDtoPage = new PageImpl<> (boardDtos, pageable, boardEntities.getTotalElements());

        System.out.println("total elements : " + boardDtoPage.getTotalElements());
        System.out.println("total pages : " + boardDtoPage.getTotalPages());
        boardDtoPage.getContent().stream().forEach(System.out::println);
    }

    // READ ALL - service applications
    @Test
    @DisplayName("findAllTest")
    public void findAllTest() {
        boardService.findAll(PageRequest.of(2, 10)).stream().forEach(System.out::println);
    }

}
