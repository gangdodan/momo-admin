package com.momo.admin.settlement.dto;

import com.momo.admin.payment.domain.enums.PayType;
import lombok.*;

import javax.persistence.Embedded;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SettlementHistoryDto {
    private LocalDateTime settleDate;
    private PayType paytype;
    private Long amount;
    private SettleAccount settleAccount;
    private Long reservationId;
    private Long meetingId;
    private Long hostId;

    public static class SettleAccount {
        private String name;
        private String account;
        private String bank;
    }
}
