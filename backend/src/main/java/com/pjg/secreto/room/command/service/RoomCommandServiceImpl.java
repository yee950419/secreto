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
    public SetRoomResponseDto setRoom(SetRoomRequestDto setRoomRequestDto) {

        SetRoomResponseDto result = new SetRoomResponseDto();
        result.setRoomNo(1L);

        return result;
    }
}
