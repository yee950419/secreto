package com.pjg.secreto.room.command.service;

import com.pjg.secreto.room.command.dto.*;

public interface RoomCommandService {
    CreateRoomResponseDto joinMember(CreateRoomRequestDto createRoomRequestDto);

    void changeRoomName(ChangeRoomNameRequestDto changeRoomNameRequestDto);

    SetRoomResponseDto setRoom(SetRoomRequestDto setRoomRequestDto);
}
