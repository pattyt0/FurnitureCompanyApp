package com.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchasesBetweenDates(LocalDate purchaseDateStart, LocalDate purchaseDateEnd) {
        return purchaseRepository.findAllByPurchaseDateBetween(purchaseDateStart, purchaseDateEnd);
    }

    public Optional<Purchase> getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }
}
