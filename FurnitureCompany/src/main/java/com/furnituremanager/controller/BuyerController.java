package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BuyerController {
    private BuyerRepository buyerRepository;

    @Autowired
    public BuyerController(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @GetMapping(value = "/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/buyers")
    public ResponseEntity<Buyer> addBuyer(@RequestBody Buyer buyer) {
        buyerRepository.save(buyer);
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }

    @DeleteMapping(value="/buyers/{buyerId}")
    public ResponseEntity<Buyer> removeBuyerById(@PathVariable Long buyerId) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if(buyer.isPresent()) {
            buyerRepository.deleteById(Long.valueOf(buyerId));
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/buyers")
    public ResponseEntity<List<Buyer>> listAllBuyers() {
        return new ResponseEntity<>(buyerRepository.findAll(), HttpStatus.OK);
    }
}
