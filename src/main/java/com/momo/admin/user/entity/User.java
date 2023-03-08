package com.momo.admin.user.entity;


import com.momo.admin.common.domain.BaseTime;
import com.momo.admin.common.exception.enums.ErrorCode;
import com.momo.admin.settlement.entity.PointHistory;
import com.momo.admin.user.enums.UserStateType;
import com.momo.admin.user.exception.CanNotChangeUserStateException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.momo.admin.user.enums.UserRole.ROLE_USER;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)       //hmm*
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTime {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Embedded
    @Builder.Default
    private Role role = new Role(List.of(ROLE_USER));

    @Embedded
    private UserState userState;

    @Embedded
    private Email email;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Password password;

    @Embedded
    @Builder.Default
    private UserPoint userPoint = new UserPoint(0L);

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointHistory> histories = new ArrayList<>();

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    public User(String email, String nickname, String password, Avatar avatar) {
        this.role = new Role(List.of(ROLE_USER));
        this.email = new Email(email);
        this.nickname = new Nickname(nickname);
        this.password = new Password(password);
        this.userPoint = new UserPoint(0L);
        this.avatar = avatar;
        this.userState = new UserState(UserStateType.ACTIVE, LocalDateTime.now());
    }

    public void changeUserState(UserState userState) {
        if(userState.isUserActive()){
            throw new CanNotChangeUserStateException(ErrorCode.UNABLE_TO_PROCESS);
        }
        setUserState(userState);
    }

    private void setUserState(UserState userState){
        this.userState = userState;
    }



}
