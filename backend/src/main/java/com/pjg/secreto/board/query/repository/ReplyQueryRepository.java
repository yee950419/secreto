package com.pjg.secreto.board.query.repository;

import com.pjg.secreto.board.common.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyQueryRepository extends JpaRepository<Reply, Long> {
}
