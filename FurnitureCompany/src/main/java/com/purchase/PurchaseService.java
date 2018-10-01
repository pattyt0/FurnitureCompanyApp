package com.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchasesBetweenDates(LocalDate purchaseDateStart, LocalDate purchaseDateEnd) {
        return purchaseRepository.findAllByPurchaseDateBetween(purchaseDateStart, purchaseDateEnd);
    }
}
