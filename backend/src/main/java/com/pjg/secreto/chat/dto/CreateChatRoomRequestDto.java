package com.pjg.secreto.chat.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateChatRoomRequestDto {

    private LocalDateTime firstTime;
}
