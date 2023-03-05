package com.momo.admin.user.entity;

import com.momo.admin.user.enums.UserStateType;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@RequiredArgsConstructor
public class UserState {
    @Enumerated(EnumType.STRING)
    private UserStateType userStateType;
    private LocalDateTime dateTime;

    public UserState(UserStateType userState, LocalDateTime dateTime) {
        this.userStateType = userState;
        this.dateTime = dateTime;
    }

    //회원 활성화 상태 확인
    public boolean isUserActive() {
        return userStateType == UserStateType.ACTIVE;
    }
}
