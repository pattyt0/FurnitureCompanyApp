package com.furnituremanager.controller.tests;

import com.furnituremanager.dao.Furniture;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @PostMapping(value = "/furniture")
    public ResponseEntity<Furniture> addFurniture(@RequestBody Furniture furniture) {
        return new ResponseEntity<>(furnitureService.saveFurniture(furniture), HttpStatus.OK);
    }

    @DeleteMapping(value="/furniture/{furnitureId}")
    public Furniture removeFurnitureById(@PathVariable Long furnitureId) throws EntityNotFoundException {
        //TODO: verify non relationship exists with this
        Furniture furniture = furnitureService.findFurniture(furnitureId);
        furnitureService.deleteFurniture(furniture);
        return furniture;
    }

    @GetMapping(value="/furniture")
    public Page<Furniture> listAllFurniture(Pageable pageable) {
        return furnitureService.findAllFurniture(pageable);
    }
}
