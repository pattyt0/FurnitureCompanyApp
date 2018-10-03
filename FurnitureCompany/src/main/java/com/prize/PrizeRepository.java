package com.prize;

import com.promotionalPeriod.PromotionalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
 * @param Furniture defines domain type
 * @param Long defines type of ID property
 */
public interface PrizeRepository extends JpaRepository<Prize, Long> {
    List<Prize> findAllByPromotionalPeriodOrderByCategoryAsc(PromotionalPeriod promotionalPeriod);
}
