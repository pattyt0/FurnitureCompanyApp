package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.LineItem;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.LineItemRepository;
import com.furnituremanager.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LineItemController {
    private LineItemRepository lineItemRepository;
    @Autowired
    PurchaseService purchaseService;

    @Autowired
    public LineItemController(LineItemRepository lineItemRepository){
        this.lineItemRepository = lineItemRepository;
    }

    @PostMapping(value = "/purchases/{purchaseId}/lineItems")
    public ResponseEntity<LineItem> addLineItem(@PathVariable Long purchaseId, @RequestBody LineItem lineItem) {
        Purchase purchase = purchaseService.findPurchaseById(purchaseId);
        if(purchase != null){
            lineItemRepository.save(lineItem);
            return new ResponseEntity<>(lineItem, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/purchases/{purchaseId}/lineItems/{lineItemId}")
    public ResponseEntity<Buyer> removeLineItemById(@PathVariable Long purchaseId, @PathVariable Long lineItemId) {
        Purchase purchase = purchaseService.findPurchaseById(purchaseId);
        if(purchase == null){
            return ResponseEntity.notFound().build();
        }

        Optional<LineItem> lineItem = lineItemRepository.findById(lineItemId);
        if(lineItem.isPresent()) {
            lineItemRepository.deleteById(lineItemId);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/purchases/{purchaseId}/lineItems")
    public ResponseEntity<List<LineItem>> listAllLineItems(@PathVariable Long purchaseId) {
        Purchase purchase = purchaseService.findPurchaseById(purchaseId);
        if(purchase == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(lineItemRepository.findAll(), HttpStatus.OK);
    }
}
