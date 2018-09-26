package com.promotionalPeriod;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "promotional_period")
@SequenceGenerator(name = "promotional_period_gen", sequenceName = "promotional_period_gen",  initialValue = 1000)
public class PromotionalPeriod implements Serializable, Comparable<PromotionalPeriod> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "promotional_period_gen")
    @Column(name="promotional_period_id")
    private Long promotionalPeriodId;
    @Column(name="promotional_period_name")
    private String promotionalPeriodName;
    @Column(name="promotional_period_start_date")
    private Instant promotionalPeriodStartDate;
    @Column(name="promotional_period_end_date")
    private Instant promotionalPeriodEndDate;

    public PromotionalPeriod(Long promotionalPeriodId) {
        this.promotionalPeriodId = promotionalPeriodId;
    }

    public Long getPromotionalPeriodId() {
        return promotionalPeriodId;
    }

    public void setPromotionalPeriodId(Long promotionalPeriodId) {
        this.promotionalPeriodId = promotionalPeriodId;
    }

    public String getPromotionalPeriodName() {
        return promotionalPeriodName;
    }

    public void setPromotionalPeriodName(String promotionalPeriodName) {
        this.promotionalPeriodName = promotionalPeriodName;
    }

    public Instant getPromotionalPeriodStartDate() {
        return promotionalPeriodStartDate;
    }

    public void setPromotionalPeriodStartDate(Instant promotionalPeriodStartDate) {
        this.promotionalPeriodStartDate = promotionalPeriodStartDate;
    }

    public Instant getPromotionalPeriodEndDate() {
        return promotionalPeriodEndDate;
    }

    public void setPromotionalPeriodEndDate(Instant promotionalPeriodEndDate) {
        this.promotionalPeriodEndDate = promotionalPeriodEndDate;
    }

    @Override
    public int compareTo(PromotionalPeriod other) {
        return this.getPromotionalPeriodId().intValue() - other.getPromotionalPeriodId().intValue();
    }
}
