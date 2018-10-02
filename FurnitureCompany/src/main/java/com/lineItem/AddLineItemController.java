package com.lineItem;

import com.buyer.Buyer;
import com.buyer.BuyerService;
import com.furniture.Furniture;
import com.purchase.Purchase;
import com.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddLineItemController {

    private LineItemRepository lineItemRepository;
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    public AddLineItemController(LineItemRepository lineItemRepository){
        this.lineItemRepository = lineItemRepository;
    }

    @RequestMapping(value = "/Purchases/{purchaseId}/LineItem", method = RequestMethod.POST)
    public ResponseEntity<Object> addLineItemForPurchase(@PathVariable Long purchaseId, @RequestBody LineItem lineItem) {
        //buyer exists
        Optional<Purchase> purchase = purchaseService.getPurchaseById(purchaseId);

        if(purchase.isPresent()){
            lineItem.setPurchase(purchase.get());
            lineItemRepository.save(lineItem);
            return new ResponseEntity<>(lineItem, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer or purchase does not exist");
        }

    }

}
