package com.raffleTicket;

import com.player.Player;
import com.player.PlayerService;
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
    PlayerService playerService;

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

        if(promotionalPeriod.isPresent() && !raffleRepository.findAllByPromotionalPeriod(promotionalPeriod.get()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period already run raffle.");
        }
        if(promotionalPeriod.isPresent()) {
            PromotionalPeriod currentPromotionalPeriod = promotionalPeriod.get();
            List<Prize> prizes = prizeService.getPrizesByPromotionalPeriod(currentPromotionalPeriod);
            if(!prizes.isEmpty()){
                List<RaffleTicket> raffleTickets = new ArrayList<>();
                for (FurniturePlayer furniturePlayer:playerTickets) {
                    raffleTickets.addAll(raffleTicketService.generateRaffleTickets(furniturePlayer, playerTickets.size(), currentPromotionalPeriod));
                    playerService.savePlayer(furniturePlayer.getPlayer());
                }

                List<RaffleTicket> winners = raffleTicketService.raffleWinnersPerPrize(raffleTickets, prizes);

                raffleRepository.saveAll(raffleTickets);

                return new ResponseEntity<>(winners, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Prizes.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period does not exists.");
        }
    }

}