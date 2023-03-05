package com.momo.admin.user.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.momo.admin.user.enums.UserRole;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@EqualsAndHashCode
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Role {
    private String roleNames;

    public Role(List<UserRole> roleNames) {
        this.roleNames = roleNames
                .stream()
                .map(Enum::toString)
                .collect(Collectors.joining(","));
    }

    public List<String> getRoles() {
        return Arrays
                .stream(roleNames.split(","))
                .collect(Collectors.toList());
    }
}
