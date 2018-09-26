package com.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AddFurnitureController {

    private FurnitureRepository furnitureRepository;

    @Autowired
    public AddFurnitureController(FurnitureRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(value = "/AddFurniture", method = RequestMethod.POST)
    public ResponseEntity<Furniture> addFurniture(@RequestBody Furniture furniture) {
        furnitureRepository.save(furniture);
        return new ResponseEntity<Furniture>(furniture, HttpStatus.OK);
    }

}
