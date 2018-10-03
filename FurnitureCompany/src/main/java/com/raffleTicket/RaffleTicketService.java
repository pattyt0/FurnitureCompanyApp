package com.raffleTicket;

import com.prize.Prize;
import com.raffleTicket.models.FurniturePlayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaffleTicketService {
    public static int startTicketSeries = 1000;

    public List<RaffleTicket> generateRaffleTickets(FurniturePlayer player, int totalTickets) {
        List<RaffleTicket> tickets = new ArrayList<>();

        RaffleTicket playerTicket = null;
        for (int i = 0; i < player.getNumberOfTickets(); i++) {
            playerTicket = new RaffleTicket();
            playerTicket.setPlayer(player.getPlayer());
            playerTicket.setTicketNumber(Double.toString(getRandomWinnerBetweenRange(startTicketSeries, startTicketSeries + (totalTickets - 1))));
            tickets.add(playerTicket);
        }

        return tickets;
    }

    public static List<RaffleTicket> raffleWinnersPerPrize(List<RaffleTicket> ticketsPerPromotionalPeriod, List<Prize> prizes) {
        List<RaffleTicket> results = new ArrayList<>();
        RaffleTicket ticketWinner = null;
        for (Prize prize:prizes) {
            int randomNumber = (int) getRandomWinnerBetweenRange(0, ticketsPerPromotionalPeriod.size()-1);
            ticketWinner = ticketsPerPromotionalPeriod.get(randomNumber);
            ticketWinner.setPrize(prize);
            results.add(ticketWinner);
        }
        return results;
    }

    public static double getRandomWinnerBetweenRange(double min, double max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

}
