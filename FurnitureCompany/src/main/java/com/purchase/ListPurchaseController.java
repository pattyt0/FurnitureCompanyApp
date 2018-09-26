package com.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<List<Purchase>>(userRepository.findAll(), HttpStatus.OK);
    }
}
