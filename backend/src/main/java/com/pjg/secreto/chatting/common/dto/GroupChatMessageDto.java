package com.pjg.secreto.chatting.common.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroupChatMessageDto {
    private Long groupChatMessageNo;
    private Long groupChatNo;
    private Long roomNo;
    private String message;
    private String sendAt;
    private String sender;
}
