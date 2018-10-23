package com.furnituremanager.controller.tests;

import com.furnituremanager.dao.*;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PromotionalPeriodController {

    @Autowired
    private PromotionalPeriodService promotionalPeriodService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PrizeService prizeService;

    @PostMapping(value = "/promotionalPeriods")
    public ResponseEntity<PromotionalPeriod> addPromotionalPeriod(@RequestBody PromotionalPeriod promotionalPeriod) {
        return new ResponseEntity<>(promotionalPeriodService.savePromotionalPeriod(promotionalPeriod), HttpStatus.OK);
    }

    @DeleteMapping(value="/promotionalPeriods/{promotionalPeriodId}")
    public ResponseEntity<PromotionalPeriod> removePromotionalPeriod(@PathVariable Long promotionalPeriodId) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);
        if(promotionalPeriod != null) {
            promotionalPeriodService.deletePromotionalPeriod(promotionalPeriod);
        }
        return new ResponseEntity<>(promotionalPeriod, HttpStatus.OK);
    }

    @GetMapping(value="/promotionalPeriods")
    public ResponseEntity<Page<PromotionalPeriod>> listAllPromotionalPeriod(Pageable pageable) {
        return new ResponseEntity<>(promotionalPeriodService.getAllPromotionalPeriods(pageable), HttpStatus.OK);
    }

    /**
     * Returns tickets available per buyer for a promotional period
     * Calculate current chances for a buyer to participate in a raffle
     * @param promotionalPeriodId
     * @param buyerId
     * @return
     */
    @GetMapping(value= "/promotionalPeriods/{promotionalPeriodId}/Buyers/{buyerId}/tickets")
    public ResponseEntity<Object> getPromotionalPeriodTicketsByBuyer(@PathVariable Long promotionalPeriodId, @PathVariable Long buyerId) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);

        if(promotionalPeriod == null){ throw new EntityNotFoundException(PromotionalPeriod.class, "id", promotionalPeriodId.toString()); }
        Buyer buyer = buyerService.getBuyer(buyerId);

        if(buyer == null){ throw new EntityNotFoundException(Buyer.class, "id", buyerId.toString()); }
        List<Ticket> promotionalPeriodTickets = purchaseService.getPurchasesBetweenPurchaseDateRageWithRaffleChances(promotionalPeriod.getPromotionStart(), promotionalPeriod.getPromotionEnd());

        return new ResponseEntity<>(promotionalPeriodTickets, HttpStatus.OK);
    }

    /**
     * once raffle per promotional period was run we could see total tickets that participating
     * @param promotionalPeriodId
     * @return
     */
    @GetMapping(value= "/promotionalPeriods/{promotionalPeriodId}/tickets")
    public ResponseEntity<Object> getPromotionalPeriodTickets(@PathVariable Long promotionalPeriodId) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);
        if(promotionalPeriod == null){ throw new EntityNotFoundException(PromotionalPeriod.class, "id", promotionalPeriodId.toString()); }

        List<Participant> tickets = participantService.findAllByPromotionalPeriod(promotionalPeriod);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    /**
     * Pick winners given a promotional period Id
     * we request list of purchases from purchase service
     * @param promotionalPeriodId
     * @return
     */
    @PutMapping(value= "/promotionalPeriods/{promotionalPeriodId}/winners")
    public ResponseEntity<Object> generateWinnersByPromotionalPeriod(@PathVariable Long promotionalPeriodId) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);

        //TODO: "findAllByPromotionalPeriod(promotionalPeriod).isEmpty()" replace with promotional period column
        if(promotionalPeriod != null && !participantService.findAllByPromotionalPeriod(promotionalPeriod).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period already run raffle.");
        }

        if(promotionalPeriod != null) {
            //get list of participants for promotional period
            List<Ticket> participants = purchaseService.getPurchasesBetweenPurchaseDateRageWithRaffleChances(promotionalPeriod.getPromotionStart(), promotionalPeriod.getPromotionEnd());

            List<Prize> prizes = prizeService.getPrizesByPromotionalPeriod(promotionalPeriod);
            if(!prizes.isEmpty()){
                List<Participant> raffleTickets = new ArrayList<>();
                for (Ticket participant:participants) {
                    raffleTickets.addAll(participantService.generateRaffleTickets(participant, promotionalPeriod));
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

    @GetMapping(value= "/promotionalPeriods/{promotionalPeriodId}/winners")
    public ResponseEntity<Object> getWinnersByPromotionalPeriod(@PathVariable Long promotionalPeriodId) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);

        if(promotionalPeriod != null) {
            return new ResponseEntity<>(participantService.getParticipantsByPromotionalPeriodAndHasWon(promotionalPeriod), HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotional Period does not exists.");
        }
    }
}
