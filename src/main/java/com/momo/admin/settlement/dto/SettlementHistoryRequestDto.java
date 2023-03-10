package com.momo.admin.settlement.dto;

import com.momo.admin.settlement.enums.DateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementHistoryRequestDto {
    private Long userId;
    private DateType dateType;
    private Long meetingId;
}
