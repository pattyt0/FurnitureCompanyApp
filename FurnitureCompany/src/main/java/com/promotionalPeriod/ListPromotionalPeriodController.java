package com.promotionalPeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ListPromotionalPeriodController {

    private PromotionalPeriodRepository promotionalPeriodRepository;

    @Autowired
    public ListPromotionalPeriodController(PromotionalPeriodRepository furnitureRepository){
        this.promotionalPeriodRepository = furnitureRepository;
    }

    @RequestMapping(value="/ListPromotionalPeriod", method= RequestMethod.GET)
    public ResponseEntity<List<PromotionalPeriod>> listAllPromotionalPeriod() {
        return new ResponseEntity<List<PromotionalPeriod>>(promotionalPeriodRepository.findAll(), HttpStatus.OK);
    }
}