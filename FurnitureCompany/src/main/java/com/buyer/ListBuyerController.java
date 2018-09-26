package com.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListBuyerController {
    private BuyerRepository buyerRepository;

    @Autowired
    public ListBuyerController(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @RequestMapping(value="/ListBuyer", method= RequestMethod.GET)
    public ResponseEntity<List<Buyer>> listAllBuyers() {
        return new ResponseEntity<>(buyerRepository.findAll(), HttpStatus.OK);
    }
}
