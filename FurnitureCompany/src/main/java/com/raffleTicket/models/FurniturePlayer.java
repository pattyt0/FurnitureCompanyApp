package com.raffleTicket.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.player.Player;

import java.time.LocalDate;

public class FurniturePlayer {

    private LocalDate purchaseDate;
    @JsonProperty("buyer")
    private Player player;
    private int numberOfTickets;

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
