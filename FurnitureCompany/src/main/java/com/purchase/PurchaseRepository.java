package com.purchase;

import com.buyer.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByBuyerAndPurchaseDateBetween(Buyer buyerId, LocalDate purchaseDateStart, LocalDate purchaseDateEnd);
    List<Purchase> findAllByPurchaseDateBetween(LocalDate purchaseDateStart, LocalDate purchaseDateEnd);
}
