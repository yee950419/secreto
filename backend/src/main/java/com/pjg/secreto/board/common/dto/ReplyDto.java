package com.pjg.secreto.board.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Getter
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    Long replyNo;
    Long boardNo;
    Long userNo;
    Long roomNo;
    String content;
    String registerAt;
    Long parentReplyNo;
    Long tagUserNo;
}
