package com.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AddPrizeController {

    private PrizeRepository prizeRepository;

    @Autowired
    public AddPrizeController(PrizeRepository prizeRepository){
        this.prizeRepository = prizeRepository;
    }

    @RequestMapping(value = "/AddPrize", method = RequestMethod.POST)
    public ResponseEntity<Prize> addPrize(@RequestBody Prize prize) {
        prizeRepository.save(prize);
        return new ResponseEntity<Prize>(prize, HttpStatus.OK);
    }

}
