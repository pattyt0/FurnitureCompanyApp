package com.furnituremanager.controller;

import com.furnituremanager.dao.Prize;
import com.furnituremanager.dao.repository.PrizeRepository;
import com.furnituremanager.dao.PromotionalPeriod;
import com.furnituremanager.service.PromotionalPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PrizeController {

    private PrizeRepository prizeRepository;

    @Autowired
    private PromotionalPeriodService promotionalPeriodService;

    @Autowired
    public PrizeController(PrizeRepository prizeRepository){
        this.prizeRepository = prizeRepository;
    }

    @RequestMapping(value = "/PromotionalPeriods/{promotionalPeriodId}/Prizes", method = RequestMethod.POST)
    public ResponseEntity<Object> addPrize(@PathVariable Long promotionalPeriodId, @RequestBody Prize prize) {

        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodService.getPromotionalPeriodById(promotionalPeriodId);
        //if promotional period exists then add promotional period to prize
        if(promotionalPeriod.isPresent()){
            prize.setPromotionalPeriod(promotionalPeriod.get());
            prizeRepository.save(prize);
            return new ResponseEntity<>(prize, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Promotional Period "+promotionalPeriodId+" does not exists.");
        }

    }

    @RequestMapping(method=RequestMethod.DELETE, value="/Prizes/{id}")
    public ResponseEntity<Prize> removePrizeById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            prizeRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/Prizes", method= RequestMethod.GET)
    public ResponseEntity<List<Prize>> listAllPrizes() {
        return new ResponseEntity<>(prizeRepository.findAll(), HttpStatus.OK);
    }

}
