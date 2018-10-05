package com.participant;

import com.buyer.Buyer;
import com.prize.Prize;
import com.promotionalPeriod.PromotionalPeriod;
import com.purchase.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;
    public static int startTicketSeries = 1000000;

    public List<Participant> generateRaffleTickets(Ticket player, int totalTickets, PromotionalPeriod promotionalPeriod) {
        List<Participant> tickets = new ArrayList<>();

        Participant playerTicket = null;
        for (int i = 0; i < player.getNumberOfTickets(); i++) {
            playerTicket = new Participant();
            playerTicket.setBuyer(player.getBuyer());
            playerTicket.setTicketNumber(Long.toString((long)(getRandomWinnerBetweenRange(startTicketSeries, startTicketSeries + (totalTickets - 1)))));
            playerTicket.setPromotionalPeriod(promotionalPeriod);
            tickets.add(playerTicket);
        }

        return tickets;
    }

    /*
    ticketsPerPromotionalPeriod should be more than number of prizes
    DEVNOTE: how should be logic for the opposite use case
    prizes are ordered by priority list
     */
    public List<Participant> raffleWinnersPerPrize(List<Participant> ticketsPerPromotionalPeriod, List<Prize> prizes) {
        List<Participant> results = new ArrayList<>();

        List<Integer> winnerTickets = new ArrayList<>();
        Participant ticketWinner = null;
        while(winnerTickets.size() < prizes.size()){
            int randomNumber = (int) getRandomWinnerBetweenRange(0, ticketsPerPromotionalPeriod.size()-1);
            ticketWinner = ticketsPerPromotionalPeriod.get(randomNumber);
            if(!ticketWinner.isWinner()){
                ticketWinner.setPrize(prizes.get(winnerTickets.size()));
                ticketWinner.setWinner(true);
                results.add(ticketWinner);
                winnerTickets.add(randomNumber);
            }

        }
        return results;
    }

    public static double getRandomWinnerBetweenRange(double min, double max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

    public List<Participant> findByBuyerAndPromotionalPeriod(Buyer buyer, PromotionalPeriod promotionalPeriod) {
        return participantRepository.findByBuyerAndPromotionalPeriod(buyer, promotionalPeriod);
    }
}
