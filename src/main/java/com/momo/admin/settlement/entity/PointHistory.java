package com.momo.admin.settlement.entity;

import com.momo.admin.common.domain.BaseTime;
import com.momo.admin.settlement.enums.PointState;
import com.momo.admin.settlement.enums.PointUsedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    private LocalDate historyDate = LocalDate.now();
    private Long currentPoint;
    private Long requestPoint;
    @Enumerated(EnumType.STRING)
    private PointState state;

    @Enumerated(EnumType.STRING)
    private PointUsedType type;

    @Column(name = "user_id")
    private Long user;

    public PointHistory(Long userId, Long currentPoint, Long requestPoint, PointState state, PointUsedType type) {
        this.user = userId;
        this.currentPoint = currentPoint;
        this.requestPoint = requestPoint;
        this.state = state;
        this.type = type;
    }
}