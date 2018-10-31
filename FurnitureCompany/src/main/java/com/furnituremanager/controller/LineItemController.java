package com.furnituremanager.controller;

import com.furnituremanager.dao.LineItem;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.LineItemService;
import com.furnituremanager.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LineItemController {
    @Autowired
    private LineItemService lineItemService;
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(value = "/purchases/{purchaseId}/lineItems")
    public LineItem addLineItem(@PathVariable Long purchaseId, @RequestBody LineItem lineItem) throws EntityNotFoundException {
        Purchase purchase = purchaseService.getPurchase(purchaseId);
        if(purchase != null){
            lineItem.setPurchase(purchase);
            return lineItemService.saveLineItem(lineItem);
        }
        throw new EntityNotFoundException(Purchase.class, "id", purchase.getPurchaseId().toString());
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
