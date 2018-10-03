package com.raffleTicket;

import com.prize.Prize;
import com.prize.PrizeService;
import com.promotionalPeriod.PromotionalPeriod;
import com.promotionalPeriod.PromotionalPeriodService;
import com.raffleTicket.models.FurniturePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RaffleTicketController {

    private RaffleTicketRepository raffleRepository;
    @Autowired
    RaffleTicketService raffleTicketService;

    @Autowired
    private PromotionalPeriodService promotionalPeriodService;
    @Autowired
    private PrizeService prizeService;

    @Autowired
    public RaffleTicketController(RaffleTicketRepository raffleRepository){
        this.raffleRepository = raffleRepository;
    }

    /*
    Pick winners given a promotional period Id
     */
    @RequestMapping(value= "/Raffle/PromotionalPeriods/{promotionalPeriodId}/Run", method= RequestMethod.POST)
    public ResponseEntity<Object> runRaffle(@PathVariable Long promotionalPeriodId, @RequestBody List<FurniturePlayer> playerTickets){

        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodService.getPromotionalPeriodById(promotionalPeriodId);

        if(promotionalPeriod.isPresent()) {
            PromotionalPeriod currentPromotionalPeriod = promotionalPeriod.get();
            List<Prize> prizes = prizeService.getPrizesByPromotionalPeriod(currentPromotionalPeriod);

            List<RaffleTicket> raffleTickets = new ArrayList<>();
            for (FurniturePlayer furniturePlayer:playerTickets) {
                raffleTickets.addAll(raffleTicketService.generateRaffleTickets(furniturePlayer, playerTickets.size()));
            }

            List<RaffleTicket> winners = raffleTicketService.raffleWinnersPerPrize(raffleTickets, prizes);

            //Save winners per promotional period
//            PromotionalPeriod promotionalPeriod = null;
//            Player player = null;
//            Prize prize = null;
//            for (RaffleTicket buyerChance:winners) {
//                raffleTicket = new Raffle();
//
//                promotionalPeriod = new PromotionalPeriod(promotionalPeriodId);
//                raffleTicket.setPromotionalPeriod(promotionalPeriod);
//                player = new Player(buyerChance.getPlayer());
//                raffleTicket.setPurchase(player);
//                prize = new Prize(buyerChance.getPrizeId());
//                raffleTicket.setPrize(prize);
//
//                raffleRepository.save(raffleTicket);
//            }

            return new ResponseEntity<>(promotionalPeriodId, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period does not exists.");
        }
    }

}