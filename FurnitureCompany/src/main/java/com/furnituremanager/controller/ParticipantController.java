package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Participant;
import com.furnituremanager.dao.repository.ParticipantRepository;
import com.furnituremanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ParticipantController {

    ParticipantRepository participantRepository;
    @Autowired
    ParticipantService raffleTicketService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    private PromotionalPeriodService promotionalPeriodService;
    @Autowired
    private PrizeService prizeService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    public ParticipantController(ParticipantRepository participantRepository){
        this.participantRepository = participantRepository;
    }

    @RequestMapping(value= "/Participants/Buyers/{buyerId}/Tickets", method= RequestMethod.GET)
    public ResponseEntity<Object> getParticipantTickets(@PathVariable Long buyerId) {
        Optional<Buyer> participant = buyerService.getBuyerById(buyerId);
        if(participant.isPresent()){
            List<Participant> tickets = participantRepository.findAllByBuyer(participant.get());
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("Buyer is not found");
        }
    }

}