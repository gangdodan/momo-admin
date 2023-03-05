package com.momo.admin.user.entity;

import com.momo.admin.user.exception.NotEnoughPointException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPoint {
    @Column(nullable = false, name = "user_point")
    private Long point;

    public UserPoint(Long point) {
        this.point = point;
    }

    public UserPoint plus(Long point) {

        return new UserPoint(this.point + point);
    }

    public UserPoint minus(Long point) {
        if (this.point - point < 0) throw new NotEnoughPointException(REQUEST_CONFLICT);
        return new UserPoint(this.point - point);
    }

    public Long getPoint() {
        return this.point;
    }
}