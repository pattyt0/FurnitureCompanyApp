package com.furnituremanager.controller;

import com.furnituremanager.dao.LineItem;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.LineItemService;
import com.furnituremanager.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LineItemController {
    @Autowired
    private LineItemService lineItemService;
    @Autowired
    PurchaseService purchaseService;

    @PostMapping(value = "/purchases/{purchaseId}/lineItems")
    public ResponseEntity<LineItem> addLineItem(@PathVariable Long purchaseId, @RequestBody LineItem lineItem) throws EntityNotFoundException {
        Purchase purchase = purchaseService.getPurchase(purchaseId);
        if(purchase != null){
            lineItemService.saveLineItem(lineItem);
            return new ResponseEntity<>(lineItem, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/purchases/{purchaseId}/lineItems/{lineItemId}")
    public LineItem removeLineItemById(@PathVariable Long purchaseId, @PathVariable Long lineItemId) throws EntityNotFoundException {
        Purchase purchase = purchaseService.getPurchase(purchaseId);

        if(purchase == null) { throw new EntityNotFoundException(LineItem.class, "id", lineItemId.toString()); }

        LineItem lineItem = lineItemService.getLineItem(lineItemId);
        lineItemService.deleteLineItem(lineItem);
        return lineItem;
    }

    @GetMapping(value="/purchases/{purchaseId}/lineItems")
    public Page<LineItem> listAllLineItems(@PathVariable Long purchaseId, Pageable pageable) throws EntityNotFoundException {
        Purchase purchase = purchaseService.getPurchase(purchaseId);
        if(purchase == null){        return Page.empty();    }

        return lineItemService.getLineItemsByPurchase(pageable);
    }
}
