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
    private String name;

    @Size(min = 3, max = 100)
    private String code;

    public Furniture(String furnitureId, String furnitureName, String furnitureCode) {
        this.setFurnitureId(Long.getLong(furnitureId));
        this.setName(furnitureName);
        this.setCode(furnitureCode);
    }

    public Furniture(){}

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(Furniture otherFurniture) {
        return this.getFurnitureId().intValue() - otherFurniture.getFurnitureId().intValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Furniture furniture = (Furniture) other;
        return this.getFurnitureId() == furniture.getFurnitureId() && this.getName().equals(furniture.getName())
                && this.getCode().equals(furniture.getCode());
    }
}
