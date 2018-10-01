package com.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeletePurchaseController {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public DeletePurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/Purchases/{id}")
    public ResponseEntity<Purchase> removePurchaseById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            purchaseRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
