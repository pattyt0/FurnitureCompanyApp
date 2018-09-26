package com.promotionalPeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AddPromotionalPeriodController {

    private PromotionalPeriodRepository furnitureRepository;

    @Autowired
    public AddPromotionalPeriodController(PromotionalPeriodRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(value = "/AddPromotionalPeriod", method = RequestMethod.POST)
    public ResponseEntity<PromotionalPeriod> addPromotionalPeriod(@RequestBody PromotionalPeriod promotionalPeriod) {
        furnitureRepository.save(promotionalPeriod);
        return new ResponseEntity<PromotionalPeriod>(promotionalPeriod, HttpStatus.OK);
    }

}
