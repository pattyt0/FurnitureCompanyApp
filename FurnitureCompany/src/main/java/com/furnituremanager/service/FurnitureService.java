package com.furnituremanager.service;

import com.furnituremanager.dao.Furniture;
import com.furnituremanager.dao.repository.FurnitureRepository;
import com.furnituremanager.errormanager.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public Furniture saveFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    public void deleteFurniture(Furniture furniture) {
        furnitureRepository.delete(furniture);
    }

    public Furniture findFurniture(Long furnitureId) throws EntityNotFoundException {
        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);
        if(!furniture.isPresent()){throw new EntityNotFoundException(Furniture.class,"id",furnitureId.toString());
        }
        return furniture.get();
    }

    public Page<Furniture> findAllFurniture(Pageable pageable) {
        return furnitureRepository.findAll(pageable);
    }
}

