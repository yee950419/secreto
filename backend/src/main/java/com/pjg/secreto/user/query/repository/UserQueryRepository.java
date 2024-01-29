package com.pjg.secreto.user.query.repository;

import com.pjg.secreto.user.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<User, Long> {
}
