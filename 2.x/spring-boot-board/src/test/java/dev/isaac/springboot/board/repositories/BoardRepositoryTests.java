package dev.isaac.springboot.board.repositories;

import dev.isaac.springboot.board.entities.BoardEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    // Create
    @Test
    @DisplayName("insertBoardTest")
    @Transactional
    public void insertBoardTest() {
            boardRepository.save(
                    BoardEntity.builder()
                            .title("안녕하십니까, 운영자 입니다.")
                            .content("반갑습니다. 저는 운영자입니다.")
                            .author("운영자")
                            .createdDate(LocalDateTime.now())
                            .build()
            );
    }

    @Test
    @DisplayName("insertBoardDataSetTest")
    @Transactional
    public void insertBoardDataSetTest() {
        for(int i = 0; i<15; i++) {
            boardRepository.save(
                    BoardEntity.builder()
                            .title("안녕하십니까, 운영자 입니다. [" + (i + boardRepository.count()) + "]")
                            .content("반갑습니다. 저는 운영자입니다. [" + (i + boardRepository.count()) + "]")
                            .author("운영자")
                            .createdDate(LocalDateTime.now())
                            .build()
            );
        }
    }

    // Read
    @Test
    @DisplayName("selectBoardTest")
    public void selectBoardTest() {
        Long id = 1L;

        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        boardEntity.ifPresent(System.out::println);

        boardRepository.findById(id).ifPresent(System.out::println);
    }

    // Read All
    @Test
    @DisplayName("selectAllPageTest")
    public void selectAllPageTest() {
        boardRepository.findAll(PageRequest.of(0, 10)).stream().forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        boardRepository.findAll(PageRequest.of(1, 10)).stream().forEach(System.out::println);
    }

    // Read All Keyword Test
    @Test
    @DisplayName("selectAllByKeyword")
    public void selectAllByKeyword() {
        String keyword = "수정";
        Pageable pageable = PageRequest.of(0, 10);
        boardRepository.findAllByTitleContainsOrContentContaining(keyword, keyword, pageable).getContent().stream().forEach(System.out::println);
    }


    // Update
    @Test
    @DisplayName("updateBoardTest")
    public void updateBoardTest() {
        Long id = 1L;
        boardRepository.findById(id).ifPresent(
                boardEntity -> {
                    boardEntity.setTitle("안녕하십니까, 운영자입니다. 수정된 제목입니다.");
                    boardEntity.setContent("반갑습니다. 내용도 수정되었습니다.");
                    boardEntity.setModifiedDate(LocalDateTime.now());
                    boardRepository.save(boardEntity);
                }
        );
        boardRepository.findById(id).ifPresent(System.out::println);
    }


    // Delete
    @Test
    @DisplayName("deleteBoardTest")
    @Transactional
    public void deleteBoardTest() {
        Long id = 1L;
        boardRepository.findById(id).ifPresent(boardEntity -> boardRepository.deleteById(boardEntity.getId()));
    }

}
