package dev.isaac.springboot.board.service;

import dev.isaac.springboot.board.dtos.BoardDto;
import dev.isaac.springboot.board.entities.BoardEntity;
import dev.isaac.springboot.board.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    // Read
    public BoardDto findById(Long id) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if (boardEntity.isPresent()) {
            return BoardDto.fromEntity(boardEntity.get());
        } else {
            return BoardDto.builder()
                    .id(0L)
                    .title("존재하지 않는 글입니다.")
                    .content("존재하지 않는 글입니다.")
                    .author("작성자 없음")
                    .createdDate(LocalDateTime.now())
                    .modifiedDate(LocalDateTime.now())
                    .build();
        }
    }

    // Delete
    public void deleteById(Long id) {
        boardRepository.findById(id).ifPresent(boardEntity -> boardRepository.deleteById(boardEntity.getId()));
    }

    // Create
    public BoardDto createBoard(BoardDto.PostRequest boardPostRequest) {
        return BoardDto.fromEntity(
                boardRepository.save(
                        BoardEntity.builder()
                                .title(boardPostRequest.getTitle())
                                .content(boardPostRequest.getContent())
                                .author(boardPostRequest.getAuthor())
                                .createdDate(LocalDateTime.now())
                                .build()
                )
        );
    }

    // modifyMoard()
    public BoardDto modifyBoard(BoardDto.PostRequest boardPostRequest) {

        Optional<BoardEntity> selectBoardEntity = boardRepository.findById(boardPostRequest.getId());
        if(selectBoardEntity.isEmpty()) {
            return null;
        }

        selectBoardEntity.ifPresent(
                boardEntity -> {
                    boardEntity.setTitle(boardPostRequest.getTitle().equals(boardEntity.getTitle()) ? boardEntity.getTitle() : boardPostRequest.getTitle());
                    boardEntity.setContent(boardPostRequest.getContent().equals(boardEntity.getContent()) ? boardEntity.getContent() : boardPostRequest.getContent());
                    boardEntity.setAuthor(boardPostRequest.getAuthor().equals(boardEntity.getAuthor()) ? boardEntity.getAuthor() : boardPostRequest.getAuthor());
                    boardEntity.setModifiedDate(LocalDateTime.now());
                    boardRepository.save(boardEntity);
                }
        );
        return BoardDto.fromEntity(selectBoardEntity.get());
    }
}
