package com.pjg.secreto.room.command.repository;

import com.pjg.secreto.room.common.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserCommandRepository extends JpaRepository<RoomUser, Long> {
}
