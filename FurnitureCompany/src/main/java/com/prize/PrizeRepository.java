package com.prize;

import org.springframework.data.jpa.repository.JpaRepository;


/*
 * @param Furniture defines domain type
 * @param Long defines type of ID property
 */
public interface PrizeRepository extends JpaRepository<Prize, Long> {
}
