package dev.isaac.springboot.board.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_BOARD")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardEntity {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    // Variable(class field) 에 @Column Annotation 을 명시하지 않아도
    // Class 에 @Entity Annotation 이 선언되면 Table 의 Column(Field) 으로 생성한다.
    private LocalDateTime modifiedDate;

}
