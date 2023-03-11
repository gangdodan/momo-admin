package com.momo.admin.settlement.dao;

import com.momo.admin.settlement.dto.SettleHistoryDto;
import com.momo.admin.settlement.enums.DateType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.momo.admin.payment.domain.QPayment.payment;
import static com.momo.admin.settlement.entity.QSettlement.settlement;

@Repository
@RequiredArgsConstructor
public class SettleHistoryQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<SettleHistoryDto> getSettleHistory(Pageable pageable, Long userId, DateType dateType) {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now();
        if (dateType.equals(DateType.ALL)) {
            return getAllSettleHistory(pageable, userId);
        } else {
            if (dateType.equals(DateType.MONTH))
                from = LocalDateTime.now().minusMonths(1);
            else if (dateType.equals(DateType.WEEK))
                from = LocalDateTime.now().minusWeeks(1);
            return getSettleHistoryByDate(pageable, userId, from, to);
        }
    }

    public Page<SettleHistoryDto> getAllSettleHistory(Pageable pageable, Long userId) {
        List<SettleHistoryDto> histories = queryFactory.select(Projections.fields(SettleHistoryDto.class,
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
        return new PageImpl<>(histories, pageable, cnt);
    }

    public Page<SettleHistoryDto> getSettleHistoryByDate(Pageable pageable, Long userId, LocalDateTime from, LocalDateTime to) {
        List<SettleHistoryDto> histories = queryFactory.select(Projections.fields(SettleHistoryDto.class,
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
                .where(settlement.createdAt.between(from,to)
                        .and(settlement.host.eq(userId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int cnt = histories.size();
        return new PageImpl<>(histories, pageable, cnt);
    }


    public List<SettleHistoryDto> getAllSettlementHistoryByMeeting(Long meetingId) {
        List<SettleHistoryDto> histories = queryFactory.select(Projections.fields(SettleHistoryDto.class,
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
