package com.pjg.secreto.board.common.entity;

import com.pjg.secreto.mission.common.entity.UserMission;
import com.pjg.secreto.room.common.entity.RoomUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Data
@Table(name = "tbl_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_user_no")
    private RoomUser roomUser;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Liked> likeds = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardEntryLog> boardEntryLogs = new ArrayList<>();

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imgUrl;

    private LocalDateTime registerAt;

    private Long hit;

    @Enumerated(value = EnumType.STRING)
    private BoardCategory boardCategory;

    private Boolean publicYn;

    private String missionCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_mission_no")
    private UserMission userMission;

    private Long likedCount;

    @Builder
    public Board(RoomUser roomUser, String title, String content, String imgUrl, LocalDateTime registerAt, Long hit, BoardCategory boardCategory, Boolean publicYn, UserMission userMission, Long likedCount, String missionCategory) {
        this.roomUser = roomUser;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.registerAt = registerAt;
        this.hit = hit;
        this.boardCategory = boardCategory;
        this.publicYn = publicYn;
        this.userMission = userMission;
        this.likedCount = likedCount;
        this.missionCategory = missionCategory;
    }

    public void updateBoard(Long id, String title, String content, String imgUrl, Boolean publicYn){
        this.id = id;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.publicYn = publicYn;
    }
    public void updateLikedCount(Long likedCount){
        this.likedCount = likedCount;
    }

    public void updateHit(Long hit){
        this.hit = hit;
    }

}