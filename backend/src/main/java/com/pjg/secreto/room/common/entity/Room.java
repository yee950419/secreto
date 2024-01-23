package com.pjg.secreto.room.common.entity;

import com.pjg.secreto.history.common.entity.TotalResult;
import com.pjg.secreto.mission.common.entity.MissionSchedule;
import com.pjg.secreto.mission.common.entity.RoomMission;
import com.pjg.secreto.mission.common.entity.SuddenMission;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_no")
    private Long id;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY)
    private TotalResult totalResult;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<MissionSchedule> missionSchedules = new ArrayList<>();

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<RoomMission> roomMissions = new ArrayList<>();

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<SuddenMission> suddenMissions = new ArrayList<>();

    private String roomName;

    private String entryCode;

    private String roomStartAt;

    private String roomEndAt;

    private boolean hostParticipantYn;

    private boolean commonYn;

    private String missionSubmitTime;

    private String missionStartAt;

    private boolean roomStartYn;

    private Long hostNo;

}
