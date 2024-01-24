package com.pjg.secreto.room.query.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchRoomUserListResponseDto {

    private Long roomUserNO;

    private Long userNo;

    private Long roomNo;

    private String userRole;

    private String userEntryAt;

    private String userLeaveAt;

    private boolean standbyYn;

    private Long usersManito;

    private Long usersManiti;

    private String nickname;

}
