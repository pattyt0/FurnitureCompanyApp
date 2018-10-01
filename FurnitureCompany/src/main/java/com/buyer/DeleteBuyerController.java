package com.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteBuyerController {
    private BuyerRepository buyerRepository;

    @Autowired
    public DeleteBuyerController(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/Buyers/{id}")
    public ResponseEntity<Buyer> removeBuyerById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            buyerRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
