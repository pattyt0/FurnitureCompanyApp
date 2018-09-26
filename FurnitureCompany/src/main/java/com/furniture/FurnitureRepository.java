package com.furniture;

import org.springframework.data.jpa.repository.JpaRepository;


/*
 * @param Furniture defines domain type
 * @param Long defines type of ID property
 */
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
}
