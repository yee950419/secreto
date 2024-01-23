package com.pjg.secreto.board.query.repository;

import com.pjg.secreto.board.common.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardQueryRepository extends JpaRepository<Board, Long> {
}
