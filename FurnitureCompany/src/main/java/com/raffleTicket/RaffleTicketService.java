package com.raffleTicket;

import com.prize.Prize;
import com.promotionalPeriod.PromotionalPeriod;
import com.raffleTicket.models.FurniturePlayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RaffleTicketService {
    public static int startTicketSeries = 1000000;

    public List<RaffleTicket> generateRaffleTickets(FurniturePlayer player, int totalTickets, PromotionalPeriod promotionalPeriod) {
        List<RaffleTicket> tickets = new ArrayList<>();

        RaffleTicket playerTicket = null;
        for (int i = 0; i < player.getNumberOfTickets(); i++) {
            playerTicket = new RaffleTicket();
            playerTicket.setPlayer(player.getPlayer());
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
    public static List<RaffleTicket> raffleWinnersPerPrize(List<RaffleTicket> ticketsPerPromotionalPeriod, List<Prize> prizes) {
        List<RaffleTicket> results = new ArrayList<>();

        List<Integer> winnerTickets = new ArrayList<>();
        RaffleTicket ticketWinner = null;
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

}
