package com.furnituremanager.service;

import com.furnituremanager.dao.repository.PrizeRepository;
import com.furnituremanager.dao.PromotionalPeriod;
import com.furnituremanager.dao.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeService {

    @Autowired
    PrizeRepository prizeRepository;

    public List<Prize> getPrizesByPromotionalPeriod(PromotionalPeriod promotionalPeriod) {
        return prizeRepository.findAllByPromotionalPeriodOrderByCategoryAsc(promotionalPeriod);
    }
}
