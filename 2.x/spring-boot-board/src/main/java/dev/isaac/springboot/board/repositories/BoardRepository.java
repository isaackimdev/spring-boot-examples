package dev.isaac.springboot.board.repositories;

import dev.isaac.springboot.board.entities.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
