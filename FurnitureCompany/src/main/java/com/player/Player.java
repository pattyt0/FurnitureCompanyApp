package com.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "player")
@SequenceGenerator(name = "player_gen", sequenceName = "player_gen", initialValue = 1000, allocationSize=1)
@JsonIgnoreProperties({ "buyerId"})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "player_gen")
    @Column(name="player_id")
    private Long playerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String personalId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
