package com.raffle.repository;

import com.raffle.dao.Prize;
import com.raffle.dao.PromotionalPeriod;
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
