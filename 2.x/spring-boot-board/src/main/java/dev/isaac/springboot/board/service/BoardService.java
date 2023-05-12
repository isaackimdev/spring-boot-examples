package dev.isaac.springboot.board.service;

import dev.isaac.springboot.board.dtos.BoardDto;
import dev.isaac.springboot.board.entities.BoardEntity;
import dev.isaac.springboot.board.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // Read ALL
    public Page<BoardDto> findAll(Pageable pageable) {
        Page<BoardEntity> boardEntities = boardRepository.findAll(pageable);
        List<BoardDto> boardDtos = boardEntities.getContent().stream().map(BoardDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(boardDtos, pageable, boardEntities.getTotalElements());
    }


}
