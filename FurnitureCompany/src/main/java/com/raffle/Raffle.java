package com.raffle;

import com.buyer.Buyer;
import com.prize.Prize;
import com.promotionalPeriod.PromotionalPeriod;

import javax.persistence.*;

@Entity
@Table(name = "raffle")
@SequenceGenerator(name = "raffle_gen", sequenceName = "raffle_gen",  initialValue = 1000)
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "raffle_gen")
    @Column(name="raffle_id")
    private Long drawId;

    @ManyToOne
    @JoinColumn(name = "promotional_period_id")
    private PromotionalPeriod promotionalPeriod;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "prize_id")
    private Prize prize;

    public Raffle() {

    }

    public Long getDrawId() {
        return drawId;
    }

    public void setDrawId(Long drawId) {
        this.drawId = drawId;
    }

    public PromotionalPeriod getPromotionalPeriod() {
        return promotionalPeriod;
    }

    public void setPromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        this.promotionalPeriod = promotionalPeriod;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}

