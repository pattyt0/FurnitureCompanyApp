package com.furnituremanager.controller;

import com.furnituremanager.dao.Furniture;
import com.furnituremanager.dao.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FurnitureController {

    private FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureController(FurnitureRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(value = "/Furniture", method = RequestMethod.POST)
    public ResponseEntity<Furniture> addFurniture(@RequestBody Furniture furniture) {
        furnitureRepository.save(furniture);
        return new ResponseEntity<>(furniture, HttpStatus.OK);
    }

    @RequestMapping(value="/Furniture", method= RequestMethod.GET)
    public ResponseEntity<List<Furniture>> listAllFurniture() {
        return new ResponseEntity<>(furnitureRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/Furniture/{id}")
    public ResponseEntity<Furniture> removeFurnitureById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            furnitureRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
