package com.pjg.secreto.board.query.repository;

import com.pjg.secreto.board.common.entity.BoardEntryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEntryLogQueryRepository extends JpaRepository<BoardEntryLog, Long>  {
}
