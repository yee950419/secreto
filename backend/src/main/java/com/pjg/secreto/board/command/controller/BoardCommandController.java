package com.pjg.secreto.board.command.controller;

import com.pjg.secreto.board.common.dto.BoardDto;
import com.pjg.secreto.common.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BoardCommandController {

    @PutMapping(value="/post")
    public ResponseEntity<?> updatePost(@RequestBody BoardDto boardDto){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "게시글 수정 성공"));
    }

    @DeleteMapping(value="/post/{boardNo}")
    public ResponseEntity<?> deletePost(@RequestBody @Valid Map<String, Object> request){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "게시글 삭제 성공"));
    }

    @PostMapping(value="/post")
    public ResponseEntity<?> createPost(@RequestBody @Valid Map<String, String> param){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "게시글 작성 성공"));
    }

    @PostMapping(value="/post/{boardNo}/like")
    public ResponseEntity<?> likePost(){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "좋아요 성공"));
    }

    @DeleteMapping(value="/post/{boardNo}/unlike")
    public ResponseEntity<?> unlikePost(){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "좋아요 취소 성공"));
    }

    @PostMapping(value="/reply/{boardNo}")
    public ResponseEntity<?> createReply(){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "댓글 작성 성공"));
    }

    @DeleteMapping(value="/reply/{replyNo}")
    public ResponseEntity<?> deleteReply(){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "댓글 삭제 성공"));
    }
}
