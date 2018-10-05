package com.furnituremanager.dao;

import javax.persistence.*;

@Entity
@Table(name = "buyer")
@SequenceGenerator(name = "buyer_gen", sequenceName = "buyer_gen", initialValue = 1000, allocationSize=1)
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "buyer_gen")
    @Column(name="buyer_id")
    private Long buyerId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String address;
    private String phone;
    @Column(name="personal_id")
    private String personalId;

    public Buyer() {}
    public Buyer(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
