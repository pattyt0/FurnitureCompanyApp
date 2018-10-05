package com.furnituremanager.dao;

import com.Application;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@EntityScan(
        basePackageClasses = {Application.class, Jsr310JpaConverters.class}
)
@Entity
@Table(name = "promotional_period")
@SequenceGenerator(name = "promotional_period_gen", sequenceName = "promotional_period_gen", initialValue = 1000, allocationSize=1)
public class PromotionalPeriod implements Serializable, Comparable<PromotionalPeriod> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "promotional_period_gen")
    @Column(name="promotional_period_id")
    private Long promotionalPeriodId;

    @Column(name="title")
    private String title;

    @Column(name="promotion_start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate promotionStart;

    @Column(name="promotion_end")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate promotionEnd;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPromotionStart() {
        return promotionStart;
    }

    public void setPromotionStart(LocalDate promotionStart) {
        this.promotionStart = promotionStart;
    }

    public LocalDate getPromotionEnd() {
        return promotionEnd;
    }

    public void setPromotionEnd(LocalDate promotionEnd) {
        this.promotionEnd = promotionEnd;
    }

    @Override
    public int compareTo(PromotionalPeriod other) {
        return this.getPromotionalPeriodId().intValue() - other.getPromotionalPeriodId().intValue();
    }
}
