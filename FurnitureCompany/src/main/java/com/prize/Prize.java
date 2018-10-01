package com.prize;

import com.promotionalPeriod.PromotionalPeriod;
import com.purchase.Purchase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prize")
@SequenceGenerator(name = "prize_gen", sequenceName = "prize_gen", initialValue = 1000, allocationSize=1)
public class Prize implements Serializable, Comparable<Prize> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "prize_gen")
    @Column(name="prize_id")
    private Long prizeId;

    private String name;

    private String quantity;

    @ManyToOne
    @JoinColumn(name = "promotional_period_id")
    private PromotionalPeriod promotionalPeriod;

    public Prize(){}

    public Prize(Long prizeId){}

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Prize other) {
        return this.getPrizeId().intValue() - other.getPrizeId().intValue();
    }
}
