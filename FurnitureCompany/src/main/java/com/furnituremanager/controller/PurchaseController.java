package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.PurchaseRepository;
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
    private PurchaseRepository purchaseRepository;
    @Autowired
    private BuyerService buyerService;

    @Autowired
    public PurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping(value = "/buyers/{buyerId}/purchases")
    public ResponseEntity<Object> addPurchaseForBuyer(@PathVariable Long buyerId, @RequestBody Purchase purchase) {
        Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
        if(buyer.isPresent()){
            purchase.setBuyer(buyer.get());
            purchaseRepository.save(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }
    }

    @DeleteMapping(value="/buyers/{buyerId}/purchases/{purchaseId}")
    public ResponseEntity<Purchase> removePurchaseById(@PathVariable Long buyerId, @PathVariable Long purchaseId) {
        Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
        if(buyer.isPresent()){
            Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
            if(purchase.isPresent()) {
                purchaseRepository.delete(purchase.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value="/buyers/{buyerId}/purchases")
    public ResponseEntity<List<Purchase>> listAllPurchasesByBuyerAndPurchaseDateBetween(@PathVariable Long buyerId, @RequestParam("from") Long purchaseDateStart, @RequestParam("to") Long purchaseDateEnd) {
        Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
        if(buyer.isPresent() && purchaseDateStart != null){
            LocalDate startDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate();
            if(startDate.equals(endDate)){
                return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDate(buyer.get(), startDate), HttpStatus.OK);
            }

            if(startDate.isAfter(endDate)){
                return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(buyer.get(), endDate, startDate), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(buyer.get(), startDate, endDate), HttpStatus.OK);
            }

        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
