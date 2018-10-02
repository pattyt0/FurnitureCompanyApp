package com.lineItem;

import com.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    List<Purchase> findAllByPurchase(Purchase purchase);
}
