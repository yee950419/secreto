package com.pjg.secreto.room.query.service;

import com.pjg.secreto.room.query.dto.SearchRoomListResponseDto;
import com.pjg.secreto.room.query.dto.SearchRoomResponseDto;
import com.pjg.secreto.room.query.dto.SearchRoomUserListResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class RoomQueryServiceImpl implements RoomQueryService{

    @Override
    public List<SearchRoomUserListResponseDto> searchRoomUserList(Long roomNo) {

        List<SearchRoomUserListResponseDto> resultList = new ArrayList<>();

        SearchRoomUserListResponseDto result1 = SearchRoomUserListResponseDto.builder()
                .roomUserNO(1L).userNo(4L).roomNo(2L).userRole("MEMBER")
                .userEntryAt("2024/01/15 00:00:00").userLeaveAt("2024/01/18 00:00:00")
                .standbyYn(false).usersManito(2L).usersManiti(3L).nickname("상하악").build();

        SearchRoomUserListResponseDto result2 = SearchRoomUserListResponseDto.builder()
                .roomUserNO(1L).userNo(4L).roomNo(2L).userRole("MEMBER")
                .userEntryAt("2024/01/15 00:00:00").userLeaveAt("2024/01/18 00:00:00")
                .standbyYn(false).usersManito(2L).usersManiti(3L).nickname("현차앙").build();

        SearchRoomUserListResponseDto result3 = SearchRoomUserListResponseDto.builder()
                .roomUserNO(1L).userNo(4L).roomNo(2L).userRole("MEMBER")
                .userEntryAt("2024/01/15 00:00:00").userLeaveAt("2024/01/18 00:00:00")
                .standbyYn(false).usersManito(2L).usersManiti(3L).nickname("승우우").build();

        SearchRoomUserListResponseDto result4 = SearchRoomUserListResponseDto.builder()
                .roomUserNO(1L).userNo(4L).roomNo(2L).userRole("MEMBER")
                .userEntryAt("2024/01/15 00:00:00").userLeaveAt("2024/01/18 00:00:00")
                .standbyYn(false).usersManito(2L).usersManiti(3L).nickname("인서엉").build();

        SearchRoomUserListResponseDto result5 = SearchRoomUserListResponseDto.builder()
                .roomUserNO(1L).userNo(4L).roomNo(2L).userRole("MEMBER")
                .userEntryAt("2024/01/15 00:00:00").userLeaveAt("2024/01/18 00:00:00")
                .standbyYn(false).usersManito(2L).usersManiti(3L).nickname("시워언").build();

        SearchRoomUserListResponseDto result6 = SearchRoomUserListResponseDto.builder()
                .roomUserNO(1L).userNo(4L).roomNo(2L).userRole("MEMBER")
                .userEntryAt("2024/01/15 00:00:00").userLeaveAt("2024/01/18 00:00:00")
                .standbyYn(false).usersManito(2L).usersManiti(3L).nickname("민지이").build();

        resultList.add(result1);
        resultList.add(result2);
        resultList.add(result3);
        resultList.add(result4);
        resultList.add(result5);
        resultList.add(result6);

        return resultList;
    }

    @Override
    public List<SearchRoomListResponseDto> searchRoomList() {

        List<SearchRoomListResponseDto> resultList = new ArrayList<>();

        SearchRoomListResponseDto result1 = SearchRoomListResponseDto.builder()
                .roomNo(1L).roomName("싸피 10기 방").entryCode("3DFSas")
                .roomStartAt("2024/01/15 00:00:00").roomEndAt("2024/01/22 00:00:00")
                .hostParticipantYn(true).commonYn(false).missionSubmitTime("12:00:00")
                .missionStartAt("2024/01/16").roomStartYn(false).standbyYn(false).build();

        SearchRoomListResponseDto result2 = SearchRoomListResponseDto.builder()
                .roomNo(1L).roomName("싸피 9기 방").entryCode("3DFSas")
                .roomStartAt("2024/01/15 00:00:00").roomEndAt("2024/01/22 00:00:00")
                .hostParticipantYn(true).commonYn(false).missionSubmitTime("12:00:00")
                .missionStartAt("2024/01/16").roomStartYn(false).standbyYn(false).build();

        SearchRoomListResponseDto result3 = SearchRoomListResponseDto.builder()
                .roomNo(1L).roomName("싸피 8기 방").entryCode("3DFSas")
                .roomStartAt("2024/01/15 00:00:00").roomEndAt("2024/01/22 00:00:00")
                .hostParticipantYn(true).commonYn(false).missionSubmitTime("12:00:00")
                .missionStartAt("2024/01/16").roomStartYn(false).standbyYn(false).build();

        resultList.add(result1);
        resultList.add(result2);
        resultList.add(result3);

        return resultList;
    }

    @Override
    public SearchRoomResponseDto searchRoom(Long roomNo) {

        SearchRoomResponseDto result = SearchRoomResponseDto.builder()
                .roomNo(1L).roomName("싸피 10기 방").entryCode("3DFSas")
                .roomStartAt("2024/01/15 00:00:00").roomEndAt("2024/01/22 00:00:00")
                .hostParticipantYn(true).commonYn(false).missionSubmitTime("12:00:00")
                .missionStartAt("2024/01/10").roomStartYn(false).build();

        return result;
    }
}