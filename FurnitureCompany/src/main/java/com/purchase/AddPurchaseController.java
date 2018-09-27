package com.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddPurchaseController {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public AddPurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    @RequestMapping(value = "/AddPurchase", method = RequestMethod.POST)
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        purchaseRepository.save(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @RequestMapping(value = "/AddPurchases", method = RequestMethod.POST)
    public ResponseEntity<List<Purchase>> addPurchase(@RequestBody List<Purchase> purchases) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : purchases) {
            result.add(purchaseRepository.save(purchase));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
