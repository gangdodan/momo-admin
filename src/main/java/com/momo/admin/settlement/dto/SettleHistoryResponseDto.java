package com.momo.admin.settlement.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SettleHistoryResponseDto {
    private LocalDate settleDate;
    private String paytype;
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
    public static SettleHistoryResponseDto of(SettleHistoryDto dto){
        return SettleHistoryResponseDto.builder()
                .settleDate(dto.getSettleDate().toLocalDate())
                .paytype(dto.getPaytype().getValue())
                .amount(dto.getAmount())
                .settleAccount(new SettleAccount())
                .reservationId(dto.getReservationId())
                .meetingId(dto.getMeetingId())
                .hostId(dto.getHostId())
                .build();

    }

}
