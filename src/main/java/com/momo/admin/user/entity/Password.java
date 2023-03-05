package com.momo.admin.user.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Embeddable;

import static javax.persistence.AccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Access(FIELD)
@NoArgsConstructor(access = PROTECTED)
public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public boolean match(String password) {
        return this.password.equals(password);
    }

    protected String getPassword() {
        return this.password;
    }
}
