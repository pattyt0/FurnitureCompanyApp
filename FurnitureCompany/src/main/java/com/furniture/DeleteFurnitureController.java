package com.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteFurnitureController {
    private FurnitureRepository furnitureRepository;

    @Autowired
    public DeleteFurnitureController(FurnitureRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(method=RequestMethod.POST, value="/DeleteFurniture/{id}")
    public ResponseEntity<Furniture> removeFurnitureById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            furnitureRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
