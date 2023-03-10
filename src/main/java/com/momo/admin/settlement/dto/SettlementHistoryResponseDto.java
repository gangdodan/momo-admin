package com.momo.admin.settlement.dto;

import com.momo.admin.payment.domain.enums.PayType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SettlementHistoryResponseDto {
    private LocalDate settleDate;
    private PayType paytype;
    private Long amount;
    private SettleAccount settleAccount;
    private Long reservationId;
    private Long meetingId;
    private Long hostId;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettleAccount {
        private String name;
        private String account;
        private String bank;
    }
    public static SettlementHistoryResponseDto of(SettlementHistoryDto dto){
        return SettlementHistoryResponseDto.builder()
                .settleDate(dto.getSettleDate().toLocalDate())
                .paytype(dto.getPaytype())
                .amount(dto.getAmount())
                .settleAccount(new SettleAccount())
                .reservationId(dto.getReservationId())
                .meetingId(dto.getMeetingId())
                .hostId(dto.getHostId())
                .build();

    }

}
