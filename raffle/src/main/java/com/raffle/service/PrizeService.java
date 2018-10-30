package com.raffle.service;

import com.raffle.dao.Prize;
import com.raffle.dao.PromotionalPeriod;
import com.raffle.repository.PrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeService {

    @Autowired
    PrizeRepository prizeRepository;

    public PrizeService(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }

    public List<Prize> getPrizesByPromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        return prizeRepository.findAllByPromotionalPeriodOrderByCategoryAsc(promotionalPeriod);
    }

    public Prize addPrize(Prize prize) {
        return prizeRepository.save(prize);
    }

    public Page<Prize> getPrizesForPromotionalPeriod(PromotionalPeriod promotionalPeriod, Pageable pageable) {
        return prizeRepository.findAllByPromotionalPeriod(promotionalPeriod, pageable);
    }
}
