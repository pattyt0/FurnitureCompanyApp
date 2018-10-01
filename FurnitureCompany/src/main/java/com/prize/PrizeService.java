package com.prize;

import com.promotionalPeriod.PromotionalPeriod;
import com.promotionalPeriod.PromotionalPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrizeService {

    @Autowired
    PrizeRepository prizeRepository;

    public List<Prize> getPrizesByPromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        return prizeRepository.findAllByPromotionalPeriod(promotionalPeriod);
    }

}
