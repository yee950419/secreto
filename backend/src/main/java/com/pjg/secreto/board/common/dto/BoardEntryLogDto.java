package com.pjg.secreto.board.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Getter
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntryLogDto {
    long boardEntryLogNo;
    long roomUserNo;
    long boardNo;
    String entryAt;
}
