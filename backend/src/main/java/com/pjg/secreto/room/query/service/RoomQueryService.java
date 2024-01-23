package com.pjg.secreto.room.query.service;

import com.pjg.secreto.room.query.dto.SearchRoomListResponseDto;
import com.pjg.secreto.room.query.dto.SearchRoomResponseDto;
import com.pjg.secreto.room.query.dto.SearchRoomUserListResponseDto;

import java.util.List;

public interface RoomQueryService {
    List<SearchRoomUserListResponseDto> searchRoomUserList(Long roomNo);

    List<SearchRoomListResponseDto> searchRoomList();

    SearchRoomResponseDto searchRoom(Long roomNo);
}
