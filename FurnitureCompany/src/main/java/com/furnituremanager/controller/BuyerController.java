package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BuyerController {
    private BuyerRepository buyerRepository;

    @Autowired
    public BuyerController(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @RequestMapping(value = "/Buyers", method = RequestMethod.POST)
    public ResponseEntity<Buyer> addBuyer(@RequestBody Buyer buyer) {
        buyerRepository.save(buyer);
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/Buyers/{id}")
    public ResponseEntity<Buyer> removeBuyerById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            buyerRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/Buyers", method= RequestMethod.GET)
    public ResponseEntity<List<Buyer>> listAllBuyers() {
        return new ResponseEntity<>(buyerRepository.findAll(), HttpStatus.OK);
    }
}
