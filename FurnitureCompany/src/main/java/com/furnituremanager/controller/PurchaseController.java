package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.PurchaseRepository;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PurchaseController {
    //TODO: change it for purchaseService
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "/buyers/{buyerId}/purchases")
    public ResponseEntity<Object> addPurchaseForBuyer(@PathVariable Long buyerId, @RequestBody Purchase purchase) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        purchase.setBuyer(buyer);
        purchaseRepository.save(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @DeleteMapping(value="/buyers/{buyerId}/purchases/{purchaseId}")
    public Purchase removePurchaseById(@PathVariable Long buyerId, @PathVariable Long purchaseId) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
        if(purchase.isPresent()) {
            purchaseRepository.delete(purchase.get());
        }
        return purchase.get();
    }

    /**
     * Assume we always receive UNIX timestamp
     * @param buyerId
     * @param purchaseDateStart
     * @param purchaseDateEnd
     * @return
     */
    @GetMapping(value="/buyers/{buyerId}/purchases")
    public ResponseEntity<List<Purchase>> listAllPurchasesByBuyerAndPurchaseDateBetween(@PathVariable Long buyerId, @RequestParam(value="from", required = false) Long purchaseDateStart, @RequestParam(value="to", required = false) Long purchaseDateEnd) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        if(purchaseDateStart == null && purchaseDateEnd == null) {
            return new ResponseEntity<>(purchaseRepository.findAllByBuyer(buyer), HttpStatus.OK);
        }

        if(purchaseDateStart != null || purchaseDateEnd !=null){
            LocalDate startDate = purchaseDateStart!=null? LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate():null;
            LocalDate endDate = purchaseDateEnd!=null?LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate():null;
            if(startDate.equals(endDate) || endDate==null){
                return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDate(buyer, startDate), HttpStatus.OK);
            }

            if(startDate.isAfter(endDate)){
                return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(buyer, endDate, startDate), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(buyer, startDate, endDate), HttpStatus.OK);
            }

        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
