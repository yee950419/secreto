package com.pjg.secreto.board.query.controller;

import com.pjg.secreto.board.common.dto.BoardDto;
import com.pjg.secreto.board.common.dto.ReplyDto;
import com.pjg.secreto.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class BoardQueryController {

    @GetMapping(value="/board")
    public ResponseEntity<?> readBoard(){
        List<BoardDto> result = new ArrayList<>();
        BoardDto board1 = new BoardDto(0,1,1,"title1","content1","2022-03-10T13:22:09",1,"boast",true,null,0,"writer1");
        BoardDto board2 = new BoardDto(1,2,1,"title2","content2","2022-03-11T13:22:09",1,"boast",true,null,0,"wrtier2");
        result.add(board1);
        result.add(board2);
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "게시글 전체 조회 성공", result));
    }

    @GetMapping(value="/post/{boardNo}")
    public ResponseEntity<?> readPost(){
        BoardDto result = new BoardDto(0,1,1,"title1","content1","2022-03-10T13:22:09",1,"boast",true,null,0,"writer1");
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "게시글 상세 조회 성공", result));
    }

    @GetMapping(value="reply/{boardNo}")
    public ResponseEntity<?> readReply(){
        List<ReplyDto> result = new ArrayList<>();
        ReplyDto reply1 = new ReplyDto(1L,1L,1L,1L,"content","2022-03-11T13:22:09",null,null);
        ReplyDto reply2 = new ReplyDto(2L,1L,1L,1L,"content","2022-03-11T13:22:09",null,null);
        result.add(reply1);
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "댓글 조회 성공", result));
    }
}
