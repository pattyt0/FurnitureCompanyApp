package com.raffle.service;

import com.raffle.dao.PromotionalPeriod;
import com.raffle.errormanager.EntityNotFoundException;
import com.raffle.repository.PromotionalPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionalPeriodService {

    @Autowired
    private PromotionalPeriodRepository promotionalPeriodRepository;

    public PromotionalPeriodService(PromotionalPeriodRepository promotionalPeriodRepository) {
        this.promotionalPeriodRepository = promotionalPeriodRepository;
    }

    public PromotionalPeriod savePromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        //verify promotional period fields
        //if error found send custom error message
        return promotionalPeriodRepository.save(promotionalPeriod);
    }

    public PromotionalPeriod getPromotionalPeriod(Long promotionalPeriodId) throws EntityNotFoundException {
        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodRepository.findById(promotionalPeriodId);
        if(!promotionalPeriod.isPresent()){ throw new EntityNotFoundException(PromotionalPeriod.class, "id", promotionalPeriod.toString()); }
        return promotionalPeriod.get();
    }

    public void deletePromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        promotionalPeriodRepository.delete(promotionalPeriod);
    }

    public Page<PromotionalPeriod> getAllPromotionalPeriods(Pageable pageable) {
        return promotionalPeriodRepository.findAll(pageable);
    }
}
