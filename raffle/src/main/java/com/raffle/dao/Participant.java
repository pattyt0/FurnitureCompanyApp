package com.raffle.dao;

import javax.persistence.*;

@Entity
@Table(name = "participant")
@SequenceGenerator(name = "participant_gen", sequenceName = "participant_gen", initialValue = 1000, allocationSize=1)
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "participant_gen")
    @Column(name="participant_id")
    private Long participantId;

    @Column(name = "buyer_id")
    private Long buyerId;

    @ManyToOne
    @JoinColumn(name = "promotional_period_id")
    private PromotionalPeriod promotionalPeriod;

    private boolean winner;

    @JoinColumn(name = "ticket_number")
    private String ticketNumber;

    @OneToOne
    @JoinColumn(name = "prize_id")
    private Prize prize;

    public Participant() {

    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public PromotionalPeriod getPromotionalPeriod() {
        return promotionalPeriod;
    }

    public void setPromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        this.promotionalPeriod = promotionalPeriod;
    }

    public Long getBuyer() {
        return buyerId;
    }

    public void setBuyer(Long buyer) {
        this.buyerId = buyer;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}

