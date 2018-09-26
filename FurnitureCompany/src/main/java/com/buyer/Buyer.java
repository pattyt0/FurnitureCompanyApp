package com.buyer;

import javax.persistence.*;

@Entity
@Table(name = "buyer")
@SequenceGenerator(name = "buyer_gen", sequenceName = "buyer_gen",  initialValue = 1000)
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "buyer_gen")
    @Column(name="buyer_id")
    private Long buyerId;
    @Column(name="buyer_name")
    private String buyerName;
    @Column(name="buyer_address")
    private String buyerAddress;
    @Column(name="buyer_phone")
    private String buyerPhone;
    @Column(name="buyer_personal_id")
    private String buyerPersonalId;

    public Buyer() {}
    public Buyer(Long buyerId) {}

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerPersonalId() {
        return buyerPersonalId;
    }

    public void setBuyerPersonalId(String buyerPersonalId) {
        this.buyerPersonalId = buyerPersonalId;
    }
}
