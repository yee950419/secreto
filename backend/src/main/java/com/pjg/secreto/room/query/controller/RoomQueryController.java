package com.pjg.secreto.room.query.controller;

import com.pjg.secreto.common.response.SuccessResponse;
import com.pjg.secreto.room.command.dto.AcceptUserRequestDto;
import com.pjg.secreto.room.query.dto.SearchRoomListResponseDto;
import com.pjg.secreto.room.query.dto.SearchRoomResponseDto;
import com.pjg.secreto.room.query.dto.SearchRoomUserListResponseDto;
import com.pjg.secreto.room.query.service.RoomQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomQueryController {

    private final RoomQueryService roomQueryService;

    @GetMapping("/user/{roomNo}")
    public ResponseEntity<?> searchRoomUserList(@PathVariable Long roomNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<SearchRoomUserListResponseDto> result = roomQueryService.searchRoomUserList(roomNo);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "유저 리스트를 조회하였습니다.", result));
    }

    @GetMapping("/user/user_room")
    public ResponseEntity<?> searchRoomList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<SearchRoomListResponseDto> result = roomQueryService.searchRoomList();

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "방 리스트를 조회하였습니다.", result));
    }

    @GetMapping("/{roomNo}")
    public ResponseEntity<?> searchRoom(@PathVariable Long roomNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        SearchRoomResponseDto result = roomQueryService.searchRoom(roomNo);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "방 정보를 조회하였습니다.", result));
    }

}