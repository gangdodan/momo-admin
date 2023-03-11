package com.momo.admin.settlement.controller;

import com.momo.admin.common.dto.PageResponseDto;
import com.momo.admin.settlement.dao.SettleHistoryQueryRepository;
import com.momo.admin.settlement.dto.SettleHistoryDto;
import com.momo.admin.settlement.dto.SettleRequestDto;
import com.momo.admin.settlement.dto.SettleHistoryResponseDto;
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
public class SettleHistoryController {
    private final SettleHistoryQueryRepository queryRepository;

    @PostMapping("/date")
    public PageResponseDto<SettleHistoryResponseDto> getAllSettlementHistory(@RequestBody SettleRequestDto request, Pageable pageable) {
        Page<SettleHistoryDto> dtos = queryRepository.getSettleHistory(pageable, request.getUserId(), request.getDateType());
        List<SettleHistoryResponseDto> list = dtos.stream().map(x -> SettleHistoryResponseDto.of(x)).collect(Collectors.toList());
        return PageResponseDto.of(new PageImpl<>(list, pageable, dtos.getSize()));
    }

    @PostMapping("/meeting")
    public List<SettleHistoryResponseDto> getAllSettlementHistoryByMeeting(@RequestBody SettleRequestDto request) {
        List<SettleHistoryDto> dtos = dtos = queryRepository.getAllSettlementHistoryByMeeting(request.getMeetingId());
        return dtos.stream().map(x -> SettleHistoryResponseDto.of(x)).collect(Collectors.toList());
    }

}

/**
 * 1.호스트+기간별 조회
 * 2.모임 건별 조회
 */