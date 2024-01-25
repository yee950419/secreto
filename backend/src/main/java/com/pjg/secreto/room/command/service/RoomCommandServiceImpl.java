package com.pjg.secreto.room.command.service;

import com.pjg.secreto.room.command.dto.*;
import com.pjg.secreto.room.command.repository.RoomCommandRepository;
import com.pjg.secreto.room.command.repository.RoomUserCommandRepository;
import com.pjg.secreto.room.common.entity.Room;
import com.pjg.secreto.room.common.entity.RoomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Transactional
@Service
public class RoomCommandServiceImpl implements RoomCommandService {

    private final RoomCommandRepository roomCommandRepository;
    private final RoomUserCommandRepository roomUserCommandRepository;


    // 방 생성 api (user 개발 완료 시 개발 예정)
    @Override
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto createRoomRequestDto) {

        // 방 생성 유저 id 꺼내기
        Long userNo = 1L;

        // 입장 코드 생성
        String entryCode = generateRandomCode();

        // 방 생성
        Room room = Room.builder().roomName(createRoomRequestDto.getRoomName())
                .hostNo(userNo).entryCode(entryCode).build();

        roomCommandRepository.save(room);

        // 방 별 유저 생성

//        RoomUser roomUser = RoomUser.builder().user()


        // 방 별 유저에 방 생성자 추가




        return null;
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

    public String generateRandomCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        String generatedCode = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedCode;
    }
}
