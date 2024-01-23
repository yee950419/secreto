package com.pjg.secreto.board.common.entity;

import com.pjg.secreto.room.common.entity.RoomUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_user_no")
    private RoomUser roomUser;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Liked> likeds = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardEntryLog> boardEntryLogs = new ArrayList<>();

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String registerAt;

    private Long hit;

    private BoardCategory boardCategory;

    private boolean publicYn;

    private String missionCategory;

    private Long likedCount;

    private String writer;
}
