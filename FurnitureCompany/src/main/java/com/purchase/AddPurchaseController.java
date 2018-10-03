package com.purchase;

import com.buyer.Buyer;
import com.buyer.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddPurchaseController {
    private PurchaseRepository purchaseRepository;
    @Autowired
    private BuyerService buyerService;

    @Autowired
    public AddPurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    @RequestMapping(value = "/Purchase/Buyers/{buyerId}", method = RequestMethod.POST)
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


    @RequestMapping(value = "/Purchases/Buyers/{buyerId}/purchaseDate/{purchaseDate}", method = RequestMethod.POST)
    public ResponseEntity<Object> addPurchaseWithPurchaseDate(@PathVariable Long buyerId, @PathVariable LocalDate purchaseDate) {
        Optional<Buyer> buyer = buyerService.getBuyerById(buyerId);
        //in progress purchase date should be epoch date
        if(buyer.isPresent()){
            Purchase purchase = new Purchase(buyer.get(), purchaseDate);
            purchaseRepository.save(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }

    }
}
