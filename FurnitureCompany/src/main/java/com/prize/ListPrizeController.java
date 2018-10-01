package com.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ListPrizeController {

    private PrizeRepository prizeRepository;

    @Autowired
    public ListPrizeController(PrizeRepository prizeRepository){
        this.prizeRepository = prizeRepository;
    }

    @RequestMapping(value="/Prizes", method= RequestMethod.GET)
    public ResponseEntity<List<Prize>> listAllPrizes() {
        return new ResponseEntity<>(prizeRepository.findAll(), HttpStatus.OK);
    }
}