package com.furnituremanager.dao.repository;

import com.furnituremanager.dao.PromotionalPeriod;
import com.furnituremanager.dao.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
 * @param Furniture defines domain type
 * @param Long defines type of ID property
 */
public interface PrizeRepository extends JpaRepository<Prize, Long> {
    List<Prize> findAllByPromotionalPeriodOrderByCategoryAsc(PromotionalPeriod promotionalPeriod);
    Page<Prize> findAllByPromotionalPeriod(PromotionalPeriod promotionalPeriod, Pageable pageable);
}
