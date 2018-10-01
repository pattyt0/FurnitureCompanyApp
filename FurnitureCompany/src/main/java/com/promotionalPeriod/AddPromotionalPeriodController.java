package com.promotionalPeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddPromotionalPeriodController {

    private PromotionalPeriodRepository promotionalPeriodRepository;

    @Autowired
    public AddPromotionalPeriodController(PromotionalPeriodRepository promotionalPeriodRepository){
        this.promotionalPeriodRepository = promotionalPeriodRepository;
    }

    @RequestMapping(value = "/AddPromotionalPeriod", method = RequestMethod.POST)
    public ResponseEntity<PromotionalPeriod> addPromotionalPeriod(@RequestBody PromotionalPeriod promotionalPeriod) {
        promotionalPeriodRepository.save(promotionalPeriod);
        return new ResponseEntity<>(promotionalPeriod, HttpStatus.OK);
    }

}
