package com.furnituremanager.dao.repository;

import com.furnituremanager.dao.LineItem;
import com.furnituremanager.dao.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    List<LineItem> findAllByPurchase(Purchase purchase);
}
