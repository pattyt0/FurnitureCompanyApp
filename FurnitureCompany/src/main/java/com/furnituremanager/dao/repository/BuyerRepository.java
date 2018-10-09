package com.furnituremanager.dao.repository;

import com.furnituremanager.dao.Buyer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
