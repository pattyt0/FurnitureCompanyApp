package com.purchase;

import com.buyer.Buyer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase")
@SequenceGenerator(name = "purchase_gen", sequenceName = "purchase_gen", initialValue = 1000, allocationSize=1)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "purchase_gen")
    @Column(name="purchase_id")
    private Long purchaseId;

    @Column(name="purchase_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Purchase(){}
    public Purchase(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Purchase(Buyer buyer, LocalDate purchaseDate) {
        this.buyer = buyer;
        this.purchaseDate = purchaseDate;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
