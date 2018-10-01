package com.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddBuyerController {
    private BuyerRepository buyerRepository;

    @Autowired
    public AddBuyerController(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @RequestMapping(value = "/Buyers", method = RequestMethod.POST)
    public ResponseEntity<Buyer> addBuyer(@RequestBody Buyer buyer) {
        buyerRepository.save(buyer);
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }
}
