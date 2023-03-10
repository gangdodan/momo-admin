package com.momo.admin.settlement.entity;

import com.momo.admin.common.domain.BaseTime;
import com.momo.admin.common.exception.enums.ErrorCode;
import com.momo.admin.settlement.enums.SettlementState;
import com.momo.admin.settlement.exception.CanNotSettleException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Settlement extends BaseTime {
    @Id
    @Column(name = "settlement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long host;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private Long paymentId;
    @Column(nullable = false)
    private Long meetingId;
    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SettlementState state;

    public Settlement(Long host, Long amount, Long paymentId, Long meetingId, Long reservationId) {
        this.host = host;
        this.amount = amount;
        this.paymentId = paymentId;
        this.meetingId = meetingId;
        this.reservationId = reservationId;
        this.state  = SettlementState.WAIT;
    }
    public boolean canChangeState(Settlement settlement){
        if(settlement==null) throw new CanNotSettleException(ErrorCode.CAN_NOT_CHANGE_SETTLEMENT_STATE);
        return true;
    }
    public void changeSettlementState(Settlement settlement,SettlementState state){
        canChangeState(settlement);
        this.state = state;
    }
}