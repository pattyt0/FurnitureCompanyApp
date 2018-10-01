package com.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByBuyerAndPurchaseDateBetween(Long buyerId, LocalDate purchaseDateStart, LocalDate purchaseDateEnd);
}
