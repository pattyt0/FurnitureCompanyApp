package com.promotionalPeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeletePromotionalPeriodController {
    private PromotionalPeriodRepository furnitureRepository;

    @Autowired
    public DeletePromotionalPeriodController(PromotionalPeriodRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
    }

    @RequestMapping(method=RequestMethod.POST, value="/DeletePromotionalPeriod/{id}")
    public ResponseEntity<PromotionalPeriod> removeFurnitureById(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)) {
            furnitureRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
