package com.raffleTicket;

import com.prize.Prize;
import com.raffleTicket.models.FurniturePlayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaffleTicketService {
    public static int startTicketSeries = 1000000;

    public List<RaffleTicket> generateRaffleTickets(FurniturePlayer player, int totalTickets) {
        List<RaffleTicket> tickets = new ArrayList<>();

        RaffleTicket playerTicket = null;
        for (int i = 0; i < player.getNumberOfTickets(); i++) {
            playerTicket = new RaffleTicket();
            playerTicket.setPlayer(player.getPlayer());
            playerTicket.setTicketNumber(Long.toString((long)(getRandomWinnerBetweenRange(startTicketSeries, startTicketSeries + (totalTickets - 1)))));
            tickets.add(playerTicket);
        }

        return tickets;
    }

    /*
    ticketsPerPromotionalPeriod should be more than number of prizes
    DEVNOTE: how should be logic for the opposite use case
     */
    public static List<RaffleTicket> raffleWinnersPerPrize(List<RaffleTicket> ticketsPerPromotionalPeriod, List<Prize> prizes) {
        List<RaffleTicket> results = new ArrayList<>();

        List<RaffleTicket> currentTickets = ticketsPerPromotionalPeriod;

        RaffleTicket ticketWinner = null;

        for(Prize prize:prizes) {
            int randomNumber = (int) getRandomWinnerBetweenRange(0, currentTickets.size()-1);
            ticketWinner = ticketsPerPromotionalPeriod.get(randomNumber);
            ticketWinner.setPrize(prize);
            results.add(ticketWinner);
            currentTickets.remove(randomNumber);
        }
        return results;
    }

    public static double getRandomWinnerBetweenRange(double min, double max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

}
