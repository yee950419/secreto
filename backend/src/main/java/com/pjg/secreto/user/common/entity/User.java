package com.pjg.secreto.user.common.entity;

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
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_no")
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RoomUser> roomUsers = new ArrayList<>();

    private String nickname;

    private String password;

    private boolean withdrawalYn;

    private String withdrawalAt;

    private String joinAt;

    private String refreshToken;

    private String profileUrl;

    private String provider;

    private String email;

    private String sub;

}
