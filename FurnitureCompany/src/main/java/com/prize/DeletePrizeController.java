package com.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeletePrizeController {
    private PrizeRepository prizeRepository;

    @Autowired
    public DeletePrizeController(PrizeRepository PrizeRepository){
        this.prizeRepository = PrizeRepository;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/Prizes/{id}")
    public ResponseEntity<Prize> removePrizeById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            prizeRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
