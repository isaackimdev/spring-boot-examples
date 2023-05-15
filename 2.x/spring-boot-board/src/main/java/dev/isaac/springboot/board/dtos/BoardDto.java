package dev.isaac.springboot.board.dtos;

import dev.isaac.springboot.board.entities.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    // 현재 굳이 Dto 로 변경해야 할 상황은 아니지만,
    // Entity 보다는 Dto 로 변환하여 사용되는 경우가 많아 아래 코드를 추가 함.
    public static BoardDto fromEntity(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .createdDate(boardEntity.getCreatedDate() == null ? null : boardEntity.getCreatedDate())
                .modifiedDate(boardEntity.getModifiedDate() == null ? null : boardEntity.getModifiedDate())
                .build();
    }

    @Getter
    public static class PostRequest {
        private String title;
        private String content;
        private String author;
    }
}
