package com.purchase;

import com.buyer.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddPurchaseController {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public AddPurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    @RequestMapping(value = "/Purchase/Buyers/{buyerId}", method = RequestMethod.POST)
    public ResponseEntity<Purchase> addPurchase(@PathVariable Long buyerId, @RequestBody Purchase purchase) {
        Buyer buyer = new Buyer(buyerId);
        purchase.setBuyer(buyer);
        purchaseRepository.save(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @RequestMapping(value = "/Purchases/Buyers/{buyerId}/purchaseDate/{purchaseDate}", method = RequestMethod.POST)
    public ResponseEntity<Purchase> addPurchaseWithPurchaseDate(@PathVariable Long buyerId, @PathVariable LocalDate purchaseDate) {
        Buyer buyer = new Buyer(buyerId);

        Purchase purchase = new Purchase(buyer, purchaseDate);
        purchaseRepository.save(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }
}
