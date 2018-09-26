package com.furniture;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "furniture")
@SequenceGenerator(name = "furniture_gen", sequenceName = "furniture_gen",  initialValue = 1000)
public class Furniture implements Serializable, Comparable<Furniture> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "furniture_gen")
    @Column(name="furniture_id")
    private Long furnitureId;

    @Size(min = 3, max = 500)
    @Column(name="furniture_name")
    private String furnitureName;

    @Size(min = 3, max = 100)
    @Column(name="furniture_code")
    private String furnitureCode;

    public Furniture(String furnitureId, String furnitureName, String furnitureCode) {
        this.setFurnitureId(Long.getLong(furnitureId));
        this.setFurnitureName(furnitureName);
        this.setFurnitureCode(furnitureCode);
    }

    public Furniture(){}

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getFurnitureCode() {
        return furnitureCode;
    }

    public void setFurnitureCode(String furnitureCode) {
        this.furnitureCode = furnitureCode;
    }

    @Override
    public int compareTo(Furniture otherFurniture) {
        return this.getFurnitureId().intValue() - otherFurniture.getFurnitureId().intValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Furniture furniture = (Furniture) other;
        return this.getFurnitureId() == furniture.getFurnitureId() && this.getFurnitureName().equals(furniture.getFurnitureName())
                && this.getFurnitureCode().equals(furniture.getFurnitureCode());
    }
}
