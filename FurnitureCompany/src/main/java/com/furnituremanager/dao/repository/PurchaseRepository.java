package com.furnituremanager.dao.repository;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Page<Purchase> findAllByBuyerAndPurchaseDateBetween(Buyer buyerId, LocalDate purchaseDateStart, LocalDate purchaseDateEnd, Pageable pageable);
    List<Purchase> findAllByPurchaseDateBetween(LocalDate purchaseDateStart, LocalDate purchaseDateEnd);
    Page<Purchase> findAllByBuyerAndPurchaseDate(Buyer buyer, LocalDate startDate, Pageable pageable);
    Page<Purchase> findAllByBuyer(Buyer buyer, Pageable pageable);
}
