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
    private String name;
    private String address;
    private String phone;
    @Column(name="personal_id")
    private String personalId;

    public Buyer() {}
    public Buyer(Long buyerId) {}

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
}
