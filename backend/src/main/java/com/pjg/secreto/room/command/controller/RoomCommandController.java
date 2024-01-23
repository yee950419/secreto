package com.pjg.secreto.room.command.controller;

import com.pjg.secreto.common.response.SuccessResponse;
import com.pjg.secreto.room.command.dto.*;
import com.pjg.secreto.room.command.service.RoomCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomCommandController {

    private final RoomCommandService roomCommandService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequestDto createRoomRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        CreateRoomResponseDto result = roomCommandService.joinMember(createRoomRequestDto);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "방이 생성되었습니다.", result));
    }

    @PutMapping
    public ResponseEntity<?> changeRoomName(@RequestBody ChangeRoomNameRequestDto changeRoomNameRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        roomCommandService.changeRoomName(changeRoomNameRequestDto);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "방 이름이 변경되었습니다.", null));
    }

    @PutMapping("/{roomNo}")
    public ResponseEntity<?> setRoom(@RequestBody SetRoomRequestDto setRoomRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        SetRoomResponseDto setRoomResponseDto = roomCommandService.setRoom(setRoomRequestDto);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "방을 시작하였습니다.", setRoomResponseDto));
    }

}
