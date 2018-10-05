package com.furnituremanager.controller;

import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.PurchaseRepository;
import com.furnituremanager.dao.Buyer;
import com.furnituremanager.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

    @PostMapping(value = "/Purchase/Buyers/{buyerId}")
    public ResponseEntity<Object> addPurchase(@PathVariable Long buyerId, @RequestBody Purchase purchase) {
        Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
        if(buyer.isPresent()){
            purchase.setBuyer(buyer.get());
            purchaseRepository.save(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }
    }


    @PostMapping(value = "/Purchases/Buyers/{buyerId}/purchaseDate/{purchaseDate}")
    public ResponseEntity<Object> addPurchaseWithPurchaseDate(@PathVariable Long buyerId, @PathVariable Long purchaseDate) {
        LocalDate date = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDate), ZoneId.systemDefault()).toLocalDate();
        Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
        //in progress purchase date should be epoch date
        if(buyer.isPresent()){
            Purchase purchase = new Purchase(buyer.get(), date);
            purchaseRepository.save(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }

    }

    @DeleteMapping(value="/Purchases/{id}")
    public ResponseEntity<Purchase> removePurchaseById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            purchaseRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value="/Purchases")
    public ResponseEntity<List<Purchase>> listAllPurchases() {
        return new ResponseEntity<>(purchaseRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/Purchase/Buyers/{buyerId}/purchaseDate/{purchaseDateStart}/purchaseDate/{purchaseDateEnd}")
    public ResponseEntity<List<Purchase>> listAllPurchasesByPurchaseDateBetween(@PathVariable Long buyerId, @PathVariable Long purchaseDateStart, @PathVariable Long purchaseDateEnd) {
        Buyer buyer = new Buyer(buyerId);
        LocalDate rangeStart = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate();
        LocalDate rangeEnd = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate();

        return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(buyer, rangeStart, rangeEnd), HttpStatus.OK);
    }

    @GetMapping(value="/Purchases/purchaseDate/{purchaseDateStart}/purchaseDate/{purchaseDateEnd}")
    public ResponseEntity<List<Purchase>> listAllPurchasesByPurchaseDateBetween(@PathVariable Long purchaseDateStart, @PathVariable Long purchaseDateEnd) {
        LocalDate rangeStart = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate();
        LocalDate rangeEnd = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate();

        return new ResponseEntity<>(purchaseRepository.findAllByPurchaseDateBetween(rangeStart, rangeEnd), HttpStatus.OK);
    }
}
