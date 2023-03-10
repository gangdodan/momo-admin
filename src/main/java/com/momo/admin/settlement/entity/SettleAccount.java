package com.momo.admin.settlement.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettleAccount {
    private String name;
    private String account;
    private String bank;
}
