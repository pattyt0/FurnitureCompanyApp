package com.raffle;

public class BuyerChance {
    private Long buyerId;
    private String chanceCode ;
    private Long prizeId;

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public BuyerChance(Long buyerId, String buyerChanceCode) {
        this.buyerId = buyerId;
        this.chanceCode = buyerChanceCode;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getChanceCode() {
        return chanceCode;
    }

    public void setChanceCode(String chanceCode) {
        this.chanceCode = chanceCode;
    }
}
