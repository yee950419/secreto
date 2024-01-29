package com.pjg.secreto.room.query.repository;

import com.pjg.secreto.room.common.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomUserQueryRepository extends JpaRepository<RoomUser, Long>  {
    Optional<RoomUser> findById(Long id);
}
