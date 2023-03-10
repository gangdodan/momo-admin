package com.momo.admin.settlement.dao;

import com.momo.admin.settlement.dto.SettlementHistoryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static com.momo.admin.payment.domain.QPayment.payment;
import static com.momo.admin.settlement.entity.QSettlement.settlement;

@Repository
@RequiredArgsConstructor
public class SettlementHistoryQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<SettlementHistoryDto> getAllSettlementHistory(Pageable pageable,Long userId){
        List<SettlementHistoryDto> histories = queryFactory.select(Projections.fields(SettlementHistoryDto.class,
                        settlement.createdAt.as("settleDate"),
                        payment.payType,
                        settlement.amount,
                        settlement.reservationId,
                        settlement.meetingId,
                        settlement.host.as("hostId"))
                )
                .from(settlement)
                .innerJoin(payment)
                .on(payment.id.eq(settlement.paymentId))
                .where(settlement.host.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int cnt = histories.size();
        return new PageImpl<>(histories,pageable,cnt);
    }

    public Page<SettlementHistoryDto> getSettlementHistoryByMonthly(Pageable pageable,Long userId){
        List<SettlementHistoryDto> histories = queryFactory.select(Projections.fields(SettlementHistoryDto.class,
                        settlement.createdAt.as("settleDate"),
                        payment.payType,
                        settlement.amount,
                        settlement.reservationId,
                        settlement.meetingId,
                        settlement.host.as("hostId"))
                )
                .from(settlement)
                .innerJoin(payment)
                .on(payment.id.eq(settlement.paymentId))
                .where(settlement.createdAt.between(LocalDateTime.now().minusMonths(1),LocalDateTime.now())
                        .and(settlement.host.eq(userId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int cnt = histories.size();
        return new PageImpl<>(histories,pageable,cnt);
    }

    public Page<SettlementHistoryDto> getSettlementHistoryByWeekly(Pageable pageable,Long userId){
        List<SettlementHistoryDto> histories = queryFactory.select(Projections.fields(SettlementHistoryDto.class,
                        settlement.createdAt.as("settleDate"),
                        payment.payType,
                        settlement.amount,
                        settlement.reservationId,
                        settlement.meetingId,
                        settlement.host.as("hostId"))
                )
                .from(settlement)
                .innerJoin(payment)
                .on(payment.id.eq(settlement.paymentId))
                .where(settlement.createdAt.between(LocalDateTime.now().minusMonths(1),LocalDateTime.now())
                        .and(settlement.host.eq(userId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int cnt = histories.size();
        return new PageImpl<>(histories,pageable,cnt);
    }

    public List<SettlementHistoryDto> getAllSettlementHistoryByMeeting(Long meetingId){
        List<SettlementHistoryDto> histories = queryFactory.select(Projections.fields(SettlementHistoryDto.class,
                        settlement.createdAt.as("settleDate"),
                        payment.payType,
                        settlement.amount,
                        settlement.reservationId,
                        settlement.meetingId,
                        settlement.host.as("hostId"))
                )
                .from(settlement)
                .innerJoin(payment)
                .on(payment.id.eq(settlement.paymentId))
                .where(settlement.meetingId.eq(meetingId))
                .fetch();
        return histories;
    }
}
