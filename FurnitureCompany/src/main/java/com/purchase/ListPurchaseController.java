package com.purchase;

import com.buyer.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class ListPurchaseController {
    private PurchaseRepository purchaseRepository;



    @Autowired
    public ListPurchaseController(PurchaseRepository furnitureRepository){
        this.purchaseRepository = furnitureRepository;
    }

    @RequestMapping(value="/Purchases", method= RequestMethod.GET)
    public ResponseEntity<List<Purchase>> listAllPurchases() {
        return new ResponseEntity<>(purchaseRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/Purchase/Buyers/{buyerId}/purchaseDate/{purchaseDateStart}/purchaseDate/{purchaseDateEnd}", method= RequestMethod.GET)
    public ResponseEntity<List<Purchase>> listAllPurchasesByPurchaseDateBetween(@PathVariable Long buyerId, @PathVariable Long purchaseDateStart, @PathVariable Long purchaseDateEnd) {
        Buyer buyer = new Buyer(buyerId);
        LocalDate rangeStart = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate();
        LocalDate rangeEnd = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate();

        return new ResponseEntity<>(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(buyer, rangeStart, rangeEnd), HttpStatus.OK);
    }

    @RequestMapping(value="/Purchases/purchaseDate/{purchaseDateStart}/purchaseDate/{purchaseDateEnd}", method= RequestMethod.GET)
    public ResponseEntity<List<Purchase>> listAllPurchasesByPurchaseDateBetween(@PathVariable Long purchaseDateStart, @PathVariable Long purchaseDateEnd) {
        LocalDate rangeStart = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate();
        LocalDate rangeEnd = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate();

        return new ResponseEntity<>(purchaseRepository.findAllByPurchaseDateBetween(rangeStart, rangeEnd), HttpStatus.OK);
    }

}
