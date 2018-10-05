package com.furnituremanager.controller;

import com.furnituremanager.dao.*;
import com.furnituremanager.dao.repository.PromotionalPeriodRepository;
import com.furnituremanager.service.BuyerService;
import com.furnituremanager.service.ParticipantService;
import com.furnituremanager.service.PrizeService;
import com.furnituremanager.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PromotionalPeriodController {

    private PromotionalPeriodRepository promotionalPeriodRepository;

    @Autowired
    BuyerService buyerService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PrizeService prizeService;

    @Autowired
    public PromotionalPeriodController(PromotionalPeriodRepository promotionalPeriodRepository){
        this.promotionalPeriodRepository = promotionalPeriodRepository;
    }

    @RequestMapping(value = "/PromotionalPeriods", method = RequestMethod.POST)
    public ResponseEntity<PromotionalPeriod> addPromotionalPeriod(@RequestBody PromotionalPeriod promotionalPeriod) {
        promotionalPeriodRepository.save(promotionalPeriod);
        return new ResponseEntity<>(promotionalPeriod, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/PromotionalPeriods/{id}")
    public ResponseEntity<PromotionalPeriod> removeFurnitureById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            promotionalPeriodRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/PromotionalPeriods", method= RequestMethod.GET)
    public ResponseEntity<List<PromotionalPeriod>> listAllPromotionalPeriod() {
        return new ResponseEntity<>(promotionalPeriodRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value= "/PromotionalPeriods/{PromotionalPeriodId}/Buyers/{buyerId}/tickets", method= RequestMethod.GET)
    public ResponseEntity<Object> getParticipantTickets(@PathVariable Long PromotionalPeriodId, @PathVariable Long buyerId) {
        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodRepository.findById(PromotionalPeriodId);
        if(promotionalPeriod.isPresent()){
            Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
            List<Participant> tickets = participantService.findByBuyerAndPromotionalPeriod(buyer.get(),promotionalPeriod.get());
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Promotional Period is not found");
    }

    /*
    Pick winners given a promotional period Id
    we request list of purchases from purchase service
     */
    @RequestMapping(value= "/PromotionalPeriods/{promotionalPeriodId}/winners", method= RequestMethod.PUT)
    public ResponseEntity<Object> getWinnersByPromotionalPeriod(@PathVariable Long promotionalPeriodId){
        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodRepository.findById(promotionalPeriodId);

        if(promotionalPeriod.isPresent() && !participantService.findAllByPromotionalPeriod(promotionalPeriod.get()).isEmpty()){
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
                    raffleTickets.addAll(participantService.generateRaffleTickets(participant, participants.size(), currentPromotionalPeriod));
                }
                List<Participant> winners = participantService.raffleWinnersPerPrize(raffleTickets, prizes);
                participantService.saveAllParticipants(raffleTickets);

                return new ResponseEntity<>(winners, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Prizes.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period does not exists.");
        }
    }
}
