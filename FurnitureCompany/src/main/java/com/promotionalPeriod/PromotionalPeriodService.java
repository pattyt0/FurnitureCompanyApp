package com.promotionalPeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionalPeriodService {

    @Autowired
    PromotionalPeriodRepository promotionalPeriodRepository;


    public Optional<PromotionalPeriod> getPromotionalPeriodById(Long promotionalPeriodId) {
        return promotionalPeriodRepository.findById(promotionalPeriodId);
    }
}
