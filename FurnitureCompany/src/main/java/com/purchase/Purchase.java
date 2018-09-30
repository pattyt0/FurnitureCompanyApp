package com.purchase;

import com.buyer.Buyer;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "purchase")
@SequenceGenerator(name = "purchase_gen", sequenceName = "purchase_gen",  initialValue = 1000)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "purchase_gen")
    @Column(name="purchase_id")
    private Long purchaseId;

    @Column(name="purchase_date")
    private Instant purchaseDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;


    public Purchase() {}

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
