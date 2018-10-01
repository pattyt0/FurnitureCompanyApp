package com.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ListPurchaseController {
    private PurchaseRepository userRepository;

    @Autowired
    public ListPurchaseController(PurchaseRepository furnitureRepository){
        this.userRepository = furnitureRepository;
    }

    @RequestMapping(value="/ListPurchases", method= RequestMethod.GET)
    public ResponseEntity<List<Purchase>> listAllPurchases() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/ListPurchases/{buyerId}/purchaseDate/{purchaseDateStart}/purchaseDate/{purchaseDateEnd}", method= RequestMethod.GET)
    public ResponseEntity<List<Purchase>> listAllPurchasesByPurchaseDateBetween(@PathVariable Long buyerId, @PathVariable LocalDate purchaseDateStart, @PathVariable LocalDate purchaseDateEnd) {
        return new ResponseEntity<>(userRepository.findAllByBuyerAndPurchaseDateBetween(buyerId, purchaseDateStart, purchaseDateEnd), HttpStatus.OK);
    }
}
