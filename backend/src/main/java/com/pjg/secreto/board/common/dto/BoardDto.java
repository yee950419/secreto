package com.pjg.secreto.board.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Getter
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    long boardNo;
    long userNo;
    long roomNo;
    String title;
    String content;
    String registerAt;
    long hit;
    String boardCategory;
    Boolean publicYn;
    String missionCategory;
    int likedCount;
    String writer;
}
