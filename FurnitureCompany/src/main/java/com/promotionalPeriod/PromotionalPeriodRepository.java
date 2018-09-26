package com.promotionalPeriod;

import org.springframework.data.jpa.repository.JpaRepository;


/*
 * @param Furniture defines domain type
 * @param Long defines type of ID property
 */
public interface PromotionalPeriodRepository extends JpaRepository<PromotionalPeriod, Long> {
}
