package com.pjg.secreto.room.command.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetRoomRequestDto {

    private String roomEndAt;

    private boolean hostParticipantYn;

    private boolean commonYn;

    private String missionSubmitTime;

    private String missionStartAt;

    private Integer period;

    private List<MissionListDto> missionList;
}
