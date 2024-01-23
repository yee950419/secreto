package com.pjg.secreto.board.query.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class BoardEntryLogDSLRepository {
    private JpaRepository query;
}
