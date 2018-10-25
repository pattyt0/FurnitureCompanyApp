package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableDiscoveryClient
@RestController
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @GetMapping(value = "/")
    public String index() {
        return "Greetings from Furniture manager!";
    }

    /**
     * TODO: make this return DTO Object(this one should have fields filtered from DAO)
     * @param buyer
     * @return
     */
    @PostMapping(value = "/buyers")
    public Buyer addBuyer(@RequestBody Buyer buyer) {
        return buyerService.saveBuyer(buyer);
    }

    @DeleteMapping(value="/buyers/{buyerId}")
    public Buyer removeBuyerById(@PathVariable Long buyerId) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        buyerService.deleteBuyer(buyer);
        return buyer;
    }

    @GetMapping(value="/buyers")
    public Page<Buyer> listAllBuyers(Pageable pageable) {
        return buyerService.getAllBuyers(pageable);
    }

    @GetMapping(value="/buyers/{buyerId}")
    public Buyer getBuyer(@PathVariable Long buyerId) throws EntityNotFoundException {
        return buyerService.getBuyer(buyerId);
    }

}
