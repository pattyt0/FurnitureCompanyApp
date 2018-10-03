package com.lineItem;

import com.purchase.Purchase;
import com.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ListLineItemsController {

    private LineItemRepository lineItemRepository;

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    public ListLineItemsController(LineItemRepository lineItemRepository){
        this.lineItemRepository = lineItemRepository;
    }

    @RequestMapping(value="/Purchases/{purchaseId}/LineItems", method= RequestMethod.GET)
    public ResponseEntity<Object> listAllLineItems(@PathVariable Long purchaseId) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(purchaseId);
        if(purchase.isPresent()){
            return new ResponseEntity<>(lineItemRepository.findAllByPurchase(purchase.get()), HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchase found");
        }
    }

    @RequestMapping(value="/LineItems", method= RequestMethod.GET)
    public ResponseEntity<List<LineItem>> listAllLineItems() {
        return new ResponseEntity<>(lineItemRepository.findAll(), HttpStatus.OK);
    }

}
