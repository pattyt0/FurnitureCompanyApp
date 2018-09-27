package com.promotionalPeriod;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name="promotional_period_start_date")
    private String promotionalPeriodStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name="promotional_period_end_date")
    private String promotionalPeriodEndDate;

    public PromotionalPeriod() { }
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

    public String getPromotionalPeriodStartDate() {
        return promotionalPeriodStartDate;
    }

    public void setPromotionalPeriodStartDate(String promotionalPeriodStartDate) {
        this.promotionalPeriodStartDate = promotionalPeriodStartDate;
    }

    public String getPromotionalPeriodEndDate() {
        return promotionalPeriodEndDate;
    }

    public void setPromotionalPeriodEndDate(String promotionalPeriodEndDate) {
        this.promotionalPeriodEndDate = promotionalPeriodEndDate;
    }

    @Override
    public int compareTo(PromotionalPeriod other) {
        return this.getPromotionalPeriodId().intValue() - other.getPromotionalPeriodId().intValue();
    }
}
