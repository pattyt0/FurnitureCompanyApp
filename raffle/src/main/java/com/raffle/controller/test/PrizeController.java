package com.raffle.controller.test;

import com.raffle.dao.Prize;
import com.raffle.dao.PromotionalPeriod;
import com.raffle.errormanager.EntityNotFoundException;
import com.raffle.service.PrizeService;
import com.raffle.service.PromotionalPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/promotionalPeriods/{promotionalPeriodId}/*")
public class PrizeController {
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private PromotionalPeriodService promotionalPeriodService;

    @PostMapping(value = "/prizes")
    public Prize addPrizes(@PathVariable Long promotionalPeriodId, @RequestBody Prize prize) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);
        if(promotionalPeriod != null)
        {
            return prizeService.addPrize(prize);
        }
        throw new EntityNotFoundException(PromotionalPeriod.class, "id", promotionalPeriodId.toString());
    }


    @GetMapping(value = "/prizes")
    public Page<Prize> getPrizes(@PathVariable Long promotionalPeriodId, Pageable pageable) throws EntityNotFoundException {
        PromotionalPeriod promotionalPeriod = promotionalPeriodService.getPromotionalPeriod(promotionalPeriodId);
        if(promotionalPeriod != null)
        {
            return prizeService.getPrizesForPromotionalPeriod(promotionalPeriod, pageable);
        }
        throw new EntityNotFoundException(PromotionalPeriod.class, "id", promotionalPeriodId.toString());
    }
}
