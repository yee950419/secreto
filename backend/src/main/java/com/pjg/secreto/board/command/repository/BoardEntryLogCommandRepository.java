package com.pjg.secreto.board.command.repository;

import com.pjg.secreto.board.common.entity.BoardEntryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEntryLogCommandRepository extends JpaRepository<BoardEntryLog, Long> {
}
