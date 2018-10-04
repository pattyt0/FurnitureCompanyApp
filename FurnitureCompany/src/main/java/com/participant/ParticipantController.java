package com.participant;

import com.prize.Prize;
import com.prize.PrizeService;
import com.promotionalPeriod.PromotionalPeriod;
import com.promotionalPeriod.PromotionalPeriodService;
import com.purchase.PurchaseService;
import com.purchase.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ParticipantController {

    private ParticipantRepository raffleRepository;
    @Autowired
    ParticipantService raffleTicketService;

    @Autowired
    private PromotionalPeriodService promotionalPeriodService;
    @Autowired
    private PrizeService prizeService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    public ParticipantController(ParticipantRepository raffleRepository){
        this.raffleRepository = raffleRepository;
    }

    /*
    Pick winners given a promotional period Id
    we request list of purchases from purchase service
     */
    @RequestMapping(value= "/PromotionalPeriods/{promotionalPeriodId}/Run", method= RequestMethod.PUT)
    public ResponseEntity<Object> runRaffle(@PathVariable Long promotionalPeriodId){
        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodService.getPromotionalPeriodById(promotionalPeriodId);

        if(promotionalPeriod.isPresent() && !raffleRepository.findAllByPromotionalPeriod(promotionalPeriod.get()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period already run raffle.");
        }

        if(promotionalPeriod.isPresent()) {
            PromotionalPeriod currentPromotionalPeriod = promotionalPeriod.get();
            //get list of participants for promotional period
            List<Ticket> participants = purchaseService.getPurchasesBetweenPurchaseDateRageWithRaffleChances(currentPromotionalPeriod.getPromotionStart(), currentPromotionalPeriod.getPromotionEnd());

            List<Prize> prizes = prizeService.getPrizesByPromotionalPeriod(currentPromotionalPeriod);
            if(!prizes.isEmpty()){
                List<Participant> raffleTickets = new ArrayList<>();
                for (Ticket participant:participants) {
                    raffleTickets.addAll(raffleTicketService.generateRaffleTickets(participant, participants.size(), currentPromotionalPeriod));
                }

                List<Participant> winners = raffleTicketService.raffleWinnersPerPrize(raffleTickets, prizes);

                raffleRepository.saveAll(raffleTickets);

                return new ResponseEntity<>(winners, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Prizes.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period does not exists.");
        }
    }

}