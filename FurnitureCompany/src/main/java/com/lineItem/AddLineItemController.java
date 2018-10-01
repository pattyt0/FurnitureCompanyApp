package com.lineItem;

import com.furniture.Furniture;
import com.furniture.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddLineItemController {

    private FurnitureRepository furnitureRepository;

    @Autowired
    public AddLineItemController(FurnitureRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(value = "/Purchases/{purchaseId}/Buyers/{buyerId}/LineItem", method = RequestMethod.POST)
    public ResponseEntity<Furniture> addLineItemForPurchase(@RequestBody Furniture furniture) {
        furnitureRepository.save(furniture);
        return new ResponseEntity<Furniture>(furniture, HttpStatus.OK);
    }

}
