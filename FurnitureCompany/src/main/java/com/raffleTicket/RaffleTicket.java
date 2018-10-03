package com.raffleTicket;

import com.player.Player;
import com.prize.Prize;
import com.promotionalPeriod.PromotionalPeriod;

import javax.persistence.*;

@Entity
@Table(name = "raffle_ticket")
@SequenceGenerator(name = "raffle_ticket_gen", sequenceName = "raffle_ticket_gen", initialValue = 1000, allocationSize=1)
public class RaffleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "raffle_ticket_gen")
    @Column(name="raffle_ticket_id")
    private Long raffleTicketId;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "promotional_period_id")
    private PromotionalPeriod promotionalPeriod;

    private boolean winner;

    @JoinColumn(name = "ticket_number")
    private String ticketNumber;

    @OneToOne
    @JoinColumn(name = "prize_id")
    private Prize prize;

    public RaffleTicket() {

    }

    public Long getRaffleTicketId() {
        return raffleTicketId;
    }

    public void setRaffleTicketId(Long raffleTicketId) {
        this.raffleTicketId = raffleTicketId;
    }

    public PromotionalPeriod getPromotionalPeriod() {
        return promotionalPeriod;
    }

    public void setPromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        this.promotionalPeriod = promotionalPeriod;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

