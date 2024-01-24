package com.pjg.secreto.room.command.service;

import com.pjg.secreto.room.command.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoomCommandServiceImpl implements RoomCommandService {

    @Override
    public CreateRoomResponseDto joinMember(CreateRoomRequestDto createRoomRequestDto) {

        CreateRoomResponseDto result = new CreateRoomResponseDto();
        result.setEntryCode("AF24dS");

        return result;
    }

    @Override
    public void changeRoomName(ChangeRoomNameRequestDto changeRoomNameRequestDto) {

    }

    @Override
    public SetRoomResponseDto setRoom(Long roomNo) {

        SetRoomResponseDto result = new SetRoomResponseDto();
        result.setRoomNo(roomNo);

        return result;
    }

    @Override
    public void enterRoom(EnterRoomRequestDto enterRoomRequestDto) {

    }

    @Override
    public void setNickname(SetNicknameRequestDto setNicknameRequestDto) {

    }

    @Override
    public void acceptUser(AcceptUserRequestDto acceptUserRequestDto) {

    }

    @Override
    public void deligateAdmin(DeligateAdminRequestDto deligateAdminRequestDto) {

    }

    @Override
    public void bookmarkRoom(BookmarkRoomRequestDto bookmarkRoomRequestDto) {

    }

    @Override
    public void terminateRoom(TerminateRoomRequestDto terminateRoomRequestDto) {

    }
}
