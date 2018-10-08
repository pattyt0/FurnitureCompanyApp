package com.furnituremanager.service;

import com.furnituremanager.dao.repository.PromotionalPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionalPeriodService {

    @Autowired
    PromotionalPeriodRepository promotionalPeriodRepository;
}
