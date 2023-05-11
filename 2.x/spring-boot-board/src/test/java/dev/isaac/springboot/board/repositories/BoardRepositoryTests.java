package dev.isaac.springboot.board.repositories;

import dev.isaac.springboot.board.entities.BoardEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    // Create
    @Test
    @DisplayName("Board - insertTest")
    @Transactional
    public void insertTest() {
        boardRepository.save(
                BoardEntity.builder()
                        .title("안녕하십니까, 운영자 입니다.")
                        .content("반갑습니다. 저는 운영자입니다.")
                        .author("운영자")
                        .createdDate(LocalDateTime.now())
                        .build()
        ).toString();
    }

    // Read
    @Test
    @DisplayName("Board - selectOneTest")
    public void selectOneTest() {
        Long id = 1L;

        // Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        // boardEntity.ifPresent(System.out::println);

        boardRepository.findById(id).ifPresent(System.out::println);
    }

    // Read All

    // Update

    // Delete


}
