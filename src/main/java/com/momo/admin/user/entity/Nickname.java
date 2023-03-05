package com.momo.admin.user.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@EqualsAndHashCode
@Access(AccessType.FIELD)
@NoArgsConstructor(access = PROTECTED)
public class Nickname {
    @Getter
    private String nickname;

    public Nickname(String nickname) {
        this.nickname = nickname;
    }
}
