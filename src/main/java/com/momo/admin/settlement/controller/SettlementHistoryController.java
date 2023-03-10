package com.momo.admin.settlement.controller;

import com.momo.admin.common.dto.PageResponseDto;
import com.momo.admin.settlement.dao.SettlementHistoryQueryRepository;
import com.momo.admin.settlement.dto.SettlementHistoryDto;
import com.momo.admin.settlement.dto.SettlementHistoryRequestDto;
import com.momo.admin.settlement.dto.SettlementHistoryResponseDto;
import com.momo.admin.settlement.enums.DateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/settlement")
public class SettlementHistoryController {
    private final SettlementHistoryQueryRepository queryRepository;

    @PostMapping("/date")
    public PageResponseDto<SettlementHistoryResponseDto> getAllSettlementHistory(@RequestBody SettlementHistoryRequestDto request, Pageable pageable) {
        Page<SettlementHistoryDto> dtos = null;
        if (request.getDateType().equals(DateType.ALL)) {
            dtos = queryRepository.getAllSettlementHistory(pageable, request.getUserId());
        } else if (request.getDateType().equals(DateType.MONTH)) {
            dtos = queryRepository.getSettlementHistoryByMonthly(pageable, request.getUserId());
        } else if (request.getDateType().equals(DateType.WEEK)) {
            dtos = queryRepository.getSettlementHistoryByWeekly(pageable, request.getUserId());
        }

        List<SettlementHistoryResponseDto> list =  dtos.stream().map(x -> SettlementHistoryResponseDto.of(x)).collect(Collectors.toList());

        return PageResponseDto.of(new PageImpl<>(list,pageable,dtos.getSize()));
    }

    @PostMapping("/meeting")
    public List<SettlementHistoryResponseDto> getAllSettlementHistoryByMeeting(@RequestBody SettlementHistoryRequestDto request) {
        List<SettlementHistoryDto> dtos = dtos = queryRepository.getAllSettlementHistoryByMeeting(request.getUserId());
        return dtos.stream().map(x -> SettlementHistoryResponseDto.of(x)).collect(Collectors.toList());
    }

}

/**
 * 1.호스트+기간별 조회
 * 2.모임 건별 조회
 */