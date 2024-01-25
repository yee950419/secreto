package com.pjg.secreto.room.command.service;

import com.pjg.secreto.room.command.dto.*;

public interface RoomCommandService {
    CreateRoomResponseDto createRoom(CreateRoomRequestDto createRoomRequestDto);

    void changeRoomName(ChangeRoomNameRequestDto changeRoomNameRequestDto);

    SetRoomResponseDto setRoom(Long roomNo);

    void enterRoom(EnterRoomRequestDto enterRoomRequestDto);

    void setNickname(SetNicknameRequestDto setNicknameRequestDto);

    void acceptUser(AcceptUserRequestDto acceptUserRequestDto);

    void deligateAdmin(DeligateAdminRequestDto deligateAdminRequestDto);

    void bookmarkRoom(BookmarkRoomRequestDto bookmarkRoomRequestDto);

    void terminateRoom(TerminateRoomRequestDto terminateRoomRequestDto);
}
