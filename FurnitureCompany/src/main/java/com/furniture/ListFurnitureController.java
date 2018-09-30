package com.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ListFurnitureController {

    private FurnitureRepository furnitureRepository;

    @Autowired
    public ListFurnitureController(FurnitureRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(value="/FurnitureTest", method= RequestMethod.GET)
    public ResponseEntity<Furniture> listFurnituresTest() {
        Furniture furniture = new Furniture();
        furniture.setName("Office Desk");
        furniture.setCode("OD1000");
        furniture.setFurnitureId(new Long(1000));
        return new ResponseEntity<Furniture>(furniture, HttpStatus.OK);
    }

    @RequestMapping(value="/ListFurniture", method= RequestMethod.GET)
    public ResponseEntity<List<Furniture>> listAllFurniture() {
        return new ResponseEntity<List<Furniture>>(furnitureRepository.findAll(), HttpStatus.OK);
    }
}