package com.promotionalPeriod;

import com.buyer.Buyer;
import com.buyer.BuyerService;
import com.participant.Participant;
import com.participant.ParticipantService;
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
public class ListPromotionalPeriodController {

    private PromotionalPeriodRepository promotionalPeriodRepository;
    @Autowired
    ParticipantService participantService;
    @Autowired
    BuyerService buyerService;

    @Autowired
    public ListPromotionalPeriodController(PromotionalPeriodRepository furnitureRepository){
        this.promotionalPeriodRepository = furnitureRepository;
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
}