package com.pjg.secreto.board.query.repository;

import com.pjg.secreto.board.common.entity.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedQueryRepository extends JpaRepository<Liked, Long> {
}
