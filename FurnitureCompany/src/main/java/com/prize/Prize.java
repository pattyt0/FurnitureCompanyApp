package com.prize;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "prize")
@SequenceGenerator(name = "prize_gen", sequenceName = "prize_gen",  initialValue = 1000)
public class Prize implements Serializable, Comparable<Prize> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "prize_gen")
    @Column(name="prize_id")
    private Long prizeId;

    @Column(name="prize_name")
    private String prizeName;

    @Column(name="prize_quantity")
    private String prizeQuantity;

    public Prize(){}

    public Prize(Long prizeId){}

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeQuantity() {
        return prizeQuantity;
    }

    public void setPrizeQuantity(String prizeQuantity) {
        this.prizeQuantity = prizeQuantity;
    }

    @Override
    public int compareTo(Prize other) {
        return this.getPrizeId().intValue() - other.getPrizeId().intValue();
    }
}
