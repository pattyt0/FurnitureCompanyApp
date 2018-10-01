package com.prize;

import com.promotionalPeriod.PromotionalPeriod;
import com.promotionalPeriod.PromotionalPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class AddPrizeController {

    private PrizeRepository prizeRepository;

    @Autowired
    private PromotionalPeriodService promotionalPeriodService;

    @Autowired
    public AddPrizeController(PrizeRepository prizeRepository){
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

}
