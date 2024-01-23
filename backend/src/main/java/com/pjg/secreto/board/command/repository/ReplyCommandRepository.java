package com.pjg.secreto.board.command.repository;

import com.pjg.secreto.board.common.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommandRepository extends JpaRepository<Reply, Long> {
}
